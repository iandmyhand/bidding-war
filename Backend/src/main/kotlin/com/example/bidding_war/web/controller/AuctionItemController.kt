package com.example.bidding_war.web.controller

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.repository.UserRepository
import com.example.bidding_war.service.AuctionItemService
import com.example.bidding_war.service.UserService
import com.example.bidding_war.web.dto.AuctionItem.AuctionItemRequest
import com.example.bidding_war.web.dto.AuctionItem.AuctionItemResponse
import com.example.bidding_war.web.dto.AuctionItem.BidRequest
import com.example.bidding_war.web.dto.User.SignInResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/api/auctionItem")
class AuctionItemController(
    val auctionItemService: AuctionItemService,
    val userService: UserService
){

    @GetMapping
    fun get_all() = ResponseEntity.ok(auctionItemService.findAll())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<AuctionItemResponse> {
        return ResponseEntity.ok(auctionItemService.findById(id))
    }

    @PostMapping
    fun register(@RequestBody request: AuctionItemRequest): ResponseEntity<String> {
        val id = auctionItemService.register(request)
        return ResponseEntity.created(URI.create("/api/auctionItem/$id")).build()
    }

    @PostMapping("/bidding")
    fun bid(@RequestBody request: BidRequest): ResponseEntity<Void> {
        val id = auctionItemService.bid(request)
        return ResponseEntity.created(URI.create("/api/auctionItem/bidding/$id")).build()
    }
}
