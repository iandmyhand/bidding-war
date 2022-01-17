package com.study.biddingwar.controller

import com.study.biddingwar.domain.dto.BiddingDto
import com.study.biddingwar.domain.dto.BiddingResultDto
import com.study.biddingwar.domain.entity.BiddingInfo
import com.study.biddingwar.service.BiddingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/v1")
class BiddingController(private val biddingService: BiddingService) {

    @GetMapping("/bidding/product/{productId}")
    fun getBiddingProduct(principal: Principal,
                          @PathVariable(name="productId")productId:Long
    ): ResponseEntity<BiddingResultDto> {
        val userId = principal.name
        val resultDto = biddingService.getBiddingWithProductAndUser(productId, userId.toLong())
        return ResponseEntity.ok().body(resultDto)
    }

    @GetMapping("/bidding/products/{productId}")
    fun getBiddingProducts(@PathVariable(name="productId")productId:Long){
        biddingService.getProductForBidding(productId)
    }

    @PostMapping("/bidding/{productId}")
    fun postBidding(principal: Principal,
                    @PathVariable(name="productId")productId:Long,
                    @RequestBody @Valid biddingDto: BiddingDto
    ): ResponseEntity<BiddingInfo> {
        biddingDto.userId = principal.name.toLong()
        biddingDto.productId = productId
        val resultDto = biddingService.bidOnProduct(biddingDto)
        return ResponseEntity.ok().body(resultDto)
    }
}