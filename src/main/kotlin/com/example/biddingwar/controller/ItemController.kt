package com.example.biddingwar.controller

import com.example.biddingwar.dto.BiddingCreateForm
import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.dto.ItemSearchForm
import com.example.biddingwar.dto.WinBidForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.entity.User
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ItemRepository
import com.example.biddingwar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/item")
class ItemController(
    @Autowired val itemRepository: ItemRepository,
    @Autowired val userRepository: UserRepository,
    @Autowired val bidRepository: BidRepository) {

    @GetMapping("/")
    fun items(session: HttpSession, model:Model): String{
        try {
            model["userId"] = session.getAttribute("userId")
        } catch(e: NullPointerException){
            val i = 1
        }
        model["items"] = itemRepository.findAll()

        return "items/list"
    }

    @GetMapping("/create")
    fun createItemForm(session: HttpSession, model:Model): String{
        val userId = session.getAttribute("userId")

        return if (userId == null){
            "redirect:/user/login"
        } else{
            model["userId"] = userId

            "items/createItemForm"
        }
    }
    @PostMapping("/create")
    fun createItem(form: ItemCreateForm, session: HttpSession, model: Model): String{
        val userId = session.getAttribute("userId")

        return if (userId == null){
            "redirect:/user/login"
        } else{
            model["item"] = itemRepository.save(form.toEntity(session.getAttribute("userId") as String))
            model["userId"] = userId

            "redirect:/welcome/hello"
        }
    }

    @GetMapping("/search")
    fun searchItemForm(session: HttpSession, model: Model): String{
        val userId = session.getAttribute("userId")

        return "items/searchItemForm"
    }
    @GetMapping("/list")
    fun listItem(form: ItemSearchForm, session: HttpSession, model: Model): String {
        model["userId"] = session.getAttribute("userId")
        model["items"] = itemRepository.findAllByProductNameContainingOrderByCreatedTimeDesc(form.productName)

        return "items/list"
    }

    @GetMapping("/detail/{itemID}")
    fun detailItembyItemId(@PathVariable itemID: Long, session: HttpSession, model: Model): String{
        var bid_list = bidRepository.findAllByItemId(itemID)!!
        var item = itemRepository.findByIdOrNull(itemID)?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This item does not exist")

        model["item"] = item
        model["itemId"] = itemID
        model["userId"] = session.getAttribute("userId")
        model["bids"] = bid_list
        model["owner"] = session.getAttribute("userId") as String == item.userId

        if (item.status == "낙찰"){
            model["winPrice"] = bidRepository.findFirstByItemIdOrderByPriceDesc(itemID).price
        } else{
            model["winPrice"] = false;
        }

        return "items/detail"
    }

    @PostMapping("/bid")
    fun bidItem(biddingCreateForm: BiddingCreateForm, session: HttpSession): String {
        var item: Item = itemRepository.findItemById(biddingCreateForm.itemId)!!

        if(session.getAttribute("userId") as String == item.userId) {
            return "redirect:/item/list"
        }
        else if(biddingCreateForm.price < item.price){
            return "redirect:/item/detail/${biddingCreateForm.itemId}"
        }
        else{
            bidRepository.save(biddingCreateForm.toEntity())
            itemRepository.save(item)

            return "redirect:/item/detail/${biddingCreateForm.itemId}"
        }
    }

    @PostMapping("/winbid")
    fun winBidItem(winBidForm: WinBidForm, session: HttpSession): String {
        var item: Item = itemRepository.findItemById(winBidForm.getItem())!!
        var user: User = userRepository.findUserByUserId(winBidForm.getUser())!!

        if (session.getAttribute("userId") as String == item.userId) {
            item.status = "낙찰"
            itemRepository.save(item)
        }

        return "redirect:/item/detail/${item.id}"
    }

}