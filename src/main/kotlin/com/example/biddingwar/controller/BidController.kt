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
    fun bids() = ResponseEntity.ok(service.getAll())

    @GetMapping("/{userId}")
    fun userBids(@PathVariable userId: Long) = ResponseEntity.ok(service.getUserBids(userId))

    @PostMapping
    fun create(@RequestBody bid: Bid, request: HttpServletRequest): ResponseEntity<String> {
        val result = service.saveBid(bid, request)

        return if (result) {
            ResponseEntity.ok(
                "입찰 (입찰가:${bid.biddingPrice})이 등록 되었습니다."
            )
        } else {
            ResponseEntity.status(404).body("입찰에 실패했습니다.")
        }
    }
}