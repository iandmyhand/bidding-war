package com.study.peoplefund.web

import com.study.peoplefund.service.ProductService
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/products")
class ProductController(val productService: ProductService) {

    @PostMapping
    fun register(@RequestBody request: ProductRequest): ResponseEntity<Void> {
        val id = productService.register(request)
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
}