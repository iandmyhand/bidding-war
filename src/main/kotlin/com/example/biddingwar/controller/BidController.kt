package com.example.biddingwar.controller

import com.example.biddingwar.database.Bid
import com.example.biddingwar.service.bid.BidService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/bids")
class BidController(val service: BidService) {
    @GetMapping
    fun bids() = service.getAll()

    @GetMapping("/{userId}")
    fun userBids(@PathVariable userId: Long) = ResponseEntity.ok(service.getBidByUserId(userId))

    @GetMapping("/{productId}")
    fun productBids(@PathVariable productId: Long) = ResponseEntity.ok(service.getByProductId(productId))

    @PostMapping
    fun create(@RequestBody bid: Bid, request: HttpServletRequest): ResponseEntity<Bid> = service.saveBid(bid, request)
}