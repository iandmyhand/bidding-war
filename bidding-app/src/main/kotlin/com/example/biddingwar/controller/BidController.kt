package com.example.biddingwar.controller

import com.example.biddingwar.database.Bid
import com.example.biddingwar.service.bid.BidService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Api(description = "입찰")
@RestController
@RequestMapping("/bids")
class BidController(val service: BidService) {
    @GetMapping
    @ApiOperation(value = "입찰목록 확인", notes = "입찰목록 GET API")
    fun bids() = service.getAll()

    @GetMapping("/{userId}")
    @ApiOperation(value = "사용자 입찰목록 확인", notes = "사용자 입찰목록 확인 GET API")
    fun userBids(@PathVariable userId: Long) = ResponseEntity.ok(service.getBidByUserId(userId))

    @GetMapping("/{productId}")
    @ApiOperation(value = "상품 입찰목록 확인", notes = "상품별 입찰목록 확인 GET API")
    fun productBids(@PathVariable productId: Long) = ResponseEntity.ok(service.getByProductId(productId))

    @PostMapping
    @ApiOperation(value = "입찰등록", notes = "입찰등록 POST API")
    fun create(@RequestBody bid: Bid, request: HttpServletRequest): ResponseEntity<Bid> = service.saveBid(bid, request)
}