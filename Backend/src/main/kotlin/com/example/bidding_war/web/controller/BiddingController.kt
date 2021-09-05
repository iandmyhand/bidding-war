package com.example.bidding_war.web.controller

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.model.Bidding
import com.example.bidding_war.service.BiddingService
import com.example.bidding_war.web.dto.AuctionItem.BidRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/bidding")
class BiddingController(val biddingService: BiddingService){

    @GetMapping
    fun get_all() = ResponseEntity.ok(biddingService.findAll())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ResponseEntity.ok(biddingService.findByAuctionId(id))
    
}