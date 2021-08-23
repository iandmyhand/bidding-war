package com.example.biddingwar.controller

import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.dto.ItemSearchForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/item")
class ItemController(@Autowired val itemRepository: ItemRepository) {

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

    @GetMapping("/detail")
    fun detailItem(session: HttpSession, model: Model, @RequestParam id: Long): String{
        model["userId"] = session.getAttribute("userId")

        model["item"] = itemRepository.findByIdOrNull(id)?:
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "This item does not exist")
        return "items/detail"
    }
}