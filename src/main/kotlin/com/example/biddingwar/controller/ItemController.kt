package com.example.biddingwar.controller

import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.repository.ItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemController(@Autowired val itemRepository: ItemRepository) {
    @GetMapping("/item")
    fun listItem(model:Model): String{
        return "listItem"
    }

    @GetMapping("/item/search")
    fun searchItemForm(): String{
        return "items/searchItemForm"
    }

    @GetMapping("/item/create")
    fun createItemForm(): String{
        return "items/createItem"
    }

    @PostMapping("/item/create")
    fun createItem(form: ItemCreateForm): String{
        println(form.toString())
        val item: Item = form.toEntity()
        println(item.toString())

        var saved : Item = itemRepository.save(item)
        println(saved.toString())

        return "";
    }


}