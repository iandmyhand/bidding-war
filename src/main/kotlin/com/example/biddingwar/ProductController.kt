package com.example.biddingwar

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/product")
class ProductController(val service: ProductService) {
    @GetMapping
    fun getProducts() =  ResponseEntity.ok(service.getAll())

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id:Long) = ResponseEntity.ok(service.getProduct(id))

    @PostMapping
    fun create(@RequestBody product: Product): ResponseEntity<String> {
        val result = service.save(product)
        return ResponseEntity.ok("created Product ${result.name}")
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id:Long, @RequestBody product: Product): ResponseEntity<String> {
        val target: Product = service.getProduct(id).get()
        target.price = product.price
        val result = service.save(target)
        return ResponseEntity.ok("updated Product ${result.name} ${result.price}")
    }
}