package com.example.bidding_war.web.controller

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.service.AuctionItemService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/auctionItem")
class AuctionItemController(val auctionItemService: AuctionItemService){

    @GetMapping
    fun get_all() = ResponseEntity.ok(auctionItemService.findAll())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ResponseEntity.ok(auctionItemService.findById(id))

    @PostMapping
    fun register(@RequestBody auctionItem: AuctionItem): ResponseEntity<String> {
        auctionItemService.register(auctionItem)
        return ResponseEntity.ok(
            "register complete $auctionItem")
    }
}
