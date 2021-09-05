package com.example.biddingwar.controller

import com.example.biddingwar.dto.BiddingCreateForm
import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.dto.ItemSearchForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ItemRepository
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
            model["item"] = itemRepository.save(form.toEntity())
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
        model["item"] = itemRepository.findByIdOrNull(itemID)?:
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "This item does not exist")

        model["itemId"] = itemID
        model["userId"] = session.getAttribute("userId")

        model["bids"] = bidRepository.findAllByItemId(itemID)!!

        return "items/detail"
    }

    @PostMapping("/bid")
    fun bidItem(biddingCreateForm: BiddingCreateForm): String {
        bidRepository.save(biddingCreateForm.toEntity())
        var item: Item = itemRepository.findItemById(biddingCreateForm.itemId)!!

        if(item.minPrice == 0 || item.minPrice > biddingCreateForm.price){
            item.minPrice = biddingCreateForm.price
            itemRepository.save(item)
        }

        return "redirect:/item/detail/{${biddingCreateForm.itemId}}"
    }

}