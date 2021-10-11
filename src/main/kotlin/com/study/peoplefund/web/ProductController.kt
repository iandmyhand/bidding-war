package com.study.peoplefund.web

import com.study.peoplefund.service.ProductService
import com.study.peoplefund.web.arguments.user.AuthInfo
import com.study.peoplefund.web.dto.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/products")
class ProductController(val productService: ProductService) {

    @PostMapping
    fun register(@RequestBody request: ProductRequest, @AuthInfo userId: Long): ResponseEntity<Void> {
        val id = productService.register(request, userId)
        return ResponseEntity.created(URI.create("/api/products/$id")).build()
    }

    @GetMapping
    fun list(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.ok(productService.list())
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.detail(id))
    }

    @PostMapping("/bidding")
    fun bid(@RequestBody request: BidRequest, @AuthInfo userId: Long): ResponseEntity<Void> {
        val id = productService.bid(request, userId)
        return ResponseEntity.created(URI.create("/api/products/bidding/$id")).build()
    }

    @GetMapping("/{productId}/bidding-list")
    fun biddingListOfProduct(@PathVariable productId: Long): ResponseEntity<List<BiddingResponse>> {
        return ResponseEntity.ok(productService.biddingListOfProduct(productId))
    }

    @PostMapping("/{productId}/status")
    fun updateStatus(
        @PathVariable productId: Long,
        @RequestBody request: BiddingStatusRequest
    ): ResponseEntity<Void> {
        productService.updateStatus(productId, request)

        return ResponseEntity.noContent().build()
    }

}