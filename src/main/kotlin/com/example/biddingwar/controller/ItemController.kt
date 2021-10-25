package com.example.biddingwar.controller

import com.example.biddingwar.dto.BiddingCreateForm
import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.dto.ItemSearchForm
import com.example.biddingwar.dto.WinBidForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ItemRepository
import com.example.biddingwar.repository.UserRepository
import com.example.biddingwar.task.biddingItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Scheduled
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

        return if (userId == null || form.price <= 0){
            "redirect:/user/login"
        } else{
            model["item"] = itemRepository.save(form.toEntity(session.getAttribute("userId") as String))
            model["userId"] = userId

            "redirect:/item/"
        }
    }

    @GetMapping("/search")
    fun searchItemForm(session: HttpSession, model: Model): String{
        return "items/searchItemForm"
    }
    @GetMapping("/list")
    fun listItem(form: ItemSearchForm, session: HttpSession, model: Model): String {
        model["userId"] = session.getAttribute("userId")
//        model["items"] = itemRepository.findAllByProductNameContainingOrderByCreatedTimeDesc(form.productName)
        model["items"] = itemRepository.findAllByStatusAndBidTimeIsLessThanEqual("입찰", Date())

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
            model["winPrice"] = -1 // bidRepository.findFirstByItemIdOrderByPriceDesc(itemID).price
        } else{
            model["winPrice"] = false;
        }

        return "items/detail"
    }

    @PostMapping("/bid")
    fun bidItem(biddingCreateForm: BiddingCreateForm, session: HttpSession): String {
        var item: Item = itemRepository.findItemById(biddingCreateForm.itemId)!!

        if(biddingCreateForm.price > item.price && item.status == "입찰" &&
                    session.getAttribute("userId") != item.userId){
            bidRepository.save(biddingCreateForm.toEntity())
            itemRepository.save(item)

            return "redirect:/item/detail/${biddingCreateForm.itemId}"
            } else {
            return "redirect:/item/detail/${biddingCreateForm.itemId}"
        }
    }

    fun biddingItem(item: Item) : Boolean{
        if(item.status != "입찰")
            return false

        item.status = "낙찰"
        itemRepository.save(item)
        return true
    }

    @PostMapping("/winbid")
    fun winBidItem(winBidForm: WinBidForm, session: HttpSession): String {
        var item: Item = itemRepository.findItemById(winBidForm.getItem())!!

        if (item.status == "입찰" && session.getAttribute("userId") == item.userId) {
            biddingItem(item)
        }

        return "redirect:/item/detail/${item.id}"
    }

    @Scheduled(cron = "0/1 * * * * *")
    fun autoBidding(@Autowired itemRepository : ItemRepository) {
        val now = Date()
        val status = "입찰"
        var items = itemRepository.findAllByStatusAndBidTimeIsLessThanEqual(status, now)

        for(item in items){
            biddingItem(item)
        }
    }

}