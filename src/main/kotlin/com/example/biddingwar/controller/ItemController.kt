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
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.server.ResponseStatusException

@Controller
@RequestMapping("/item")
class ItemController(@Autowired val itemRepository: ItemRepository) {

    @GetMapping("/")
    fun items(model:Model): String{
        model["items"] = itemRepository.findAll()

        return "items/list"
    }

    @GetMapping("/create")
    fun createItemForm(): String{
        return "items/createItemForm"
    }
    @PostMapping("/create")
    fun createItem(form: ItemCreateForm, model: Model): String{
        var item : Item = itemRepository.save(form.toEntity())

        println(item.toString())
        model["item"] = item

        return "items/detail";
    }

    @GetMapping("/search")
    fun searchItemForm(): String{
        return "items/searchItemForm"
    }
    @GetMapping("/list")
    fun listItem(form: ItemSearchForm, model: Model): String {
        model["items"] = itemRepository.findAllByProductNameContainingOrderByCreatedTimeDesc(form.productName)

        return "items/list"
    }

    @GetMapping("/detail{id}")
    fun detailItem(model: Model, @PathVariable id: String): String{
        model["item"] = itemRepository.findByIdOrNull(id.toInt().toLong())?:
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "This item does not exist")
        return "items/detail"
    }
}