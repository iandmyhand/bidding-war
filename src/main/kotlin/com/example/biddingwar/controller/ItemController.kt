package com.example.biddingwar.controller

import com.example.biddingwar.dto.BiddingCreateForm
import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.dto.ItemSearchForm
import com.example.biddingwar.dto.WinBidForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ItemRepository
import com.example.biddingwar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
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
        return try {
            model["userId"] = session.getAttribute("userId")
            model["items"] = itemRepository.findAll()

            "items/list"
        } catch(e: NullPointerException){
            "redirect:/user/login"
        }
    }

    @GetMapping("/create")
    fun createItemForm(session: HttpSession, model:Model): String{
        return try {
            model["userId"] = session.getAttribute("userId")
            model["items"] = itemRepository.findAll()

            "items/createItemForm"
        } catch(e: NullPointerException){
            "redirect:/user/login"
        }
    }
    @PostMapping("/create")
    fun createItem(form: ItemCreateForm, session: HttpSession, model: Model): String{
        if (form.price <= 0){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "제품 가격은 0원 보다 커야 합니다.")
        }

        return try {
            model["userId"] = session.getAttribute("userId")
            model["items"] = itemRepository.findAll()

            "redirect:/item/"
        } catch(e: NullPointerException){
            "redirect:/user/login"
        }
    }

    @GetMapping("/search")
    fun searchItemForm(session: HttpSession, model: Model): String{
        return "items/searchItemForm"
    }
    @GetMapping("/list")
    fun listItem(form: ItemSearchForm, session: HttpSession, model: Model): String {
        model["userId"] = session.getAttribute("userId")
        model["items"] = itemRepository.findAllByStatusAndBidTimeIsLessThanEqual("입찰", Date())

        return "items/list"
    }

    @GetMapping("/detail/{itemID}")
    fun detailItembyItemId(@PathVariable itemID: Long, session: HttpSession, model: Model): String{
        var bid_list = bidRepository.findAllByItemId(itemID)!!
        var item = itemRepository.findItemById(itemID)?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이템을 찾을 수 없습니다.")

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
        var item: Item = itemRepository.findItemById(biddingCreateForm.itemId)?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이템을 찾을 수 없습니다.")

        if(biddingCreateForm.price < item.price){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "입찰 가격은 제춤 가격 이상이어야 합니다.")
        }
        if(item.status != "입찰"){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "제품 상태가 '입찰' 상태일 경우에만 입찰 할 수 있습니다.")
        }
        if(session.getAttribute("userId") == item.userId){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "자신의 제품에 입찰할 수 없습니다.")
        }

        bidRepository.save(biddingCreateForm.toEntity())
        itemRepository.save(item)

        return "redirect:/item/detail/${biddingCreateForm.itemId}"
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
        var item: Item = itemRepository.findItemById(winBidForm.getItem())?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이템을 찾을 수 없습니다.")

        if (item.status == "입찰" && session.getAttribute("userId") == item.userId) {
            biddingItem(item)
        }

        return "redirect:/item/detail/${item.id}"
    }

//    @Scheduled(cron = "0/1 * * * * *")
//    fun autoBidding() {
//        val now = Date()
//        val status = "입찰"
//        var items = itemRepository.findAllByStatusAndBidTimeIsLessThanEqual(status, now)
//
//        for(item in items){
//            biddingItem(item)
//        }
//    }

}