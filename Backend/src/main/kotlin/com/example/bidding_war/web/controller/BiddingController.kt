package com.example.bidding_war.web.controller

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.model.Bidding
import com.example.bidding_war.service.BiddingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bidding")
class BiddingController(val biddingService: BiddingService){

    @GetMapping
    fun get_all() = ResponseEntity.ok(biddingService.findAll())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ResponseEntity.ok(biddingService.findById(id))

    @PostMapping
    fun register(@RequestBody bidding: Bidding): ResponseEntity<String> {
        biddingService.register(bidding)
        return ResponseEntity.ok(
            "register complete $bidding")
    }
}