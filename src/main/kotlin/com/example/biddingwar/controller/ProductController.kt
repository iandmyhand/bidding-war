package com.example.biddingwar.controller

import com.example.biddingwar.database.Product
import com.example.biddingwar.service.product.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@RequestMapping("/products")
class ProductController(val service: ProductService) {
    @GetMapping
    fun products() = service.getAll()

    @GetMapping("/{id}")
    fun product(@PathVariable id: Long) = service.get(id)

    @PostMapping
    fun create(@Valid @RequestBody product: Product, request: HttpServletRequest) = service.save(product, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)

    @PutMapping("/bid/{productId}")
    fun sell(@PathVariable productId: Long, request: HttpServletRequest) = ResponseEntity.ok(
        service.sell(productId, request)
    )
}