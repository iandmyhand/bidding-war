package com.example.biddingwar.controller

import com.example.biddingwar.database.Product
import com.example.biddingwar.service.product.ProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import jdk.jfr.Description
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@Api(description = "상품")
@RestController
@RequestMapping("/products")
class ProductController(val service: ProductService) {
    @GetMapping
    @ApiOperation(value = "상품 조회", notes = "상품 조회 GET API")
    fun products() = ResponseEntity.ok(service.getAll())

    @GetMapping("/{id}")
    @ApiOperation(value = "ID별 상품조회", notes = "ID별 상품 조희 GET API")
    fun product(@PathVariable id: Long) = ResponseEntity.ok(service.get(id))

    @PostMapping
    @ApiOperation(value = "상품 등록", notes = "상품 등록 POST API")
    fun create(@Valid @RequestBody product: Product, request: HttpServletRequest) = ResponseEntity.ok(
        service.save(product)
    )

    @DeleteMapping("/{id}")
    @ApiOperation(value = "상품 삭제", notes = "상품 삭제 DELETE API")
    fun delete(@PathVariable id: Long): ResponseEntity<String> {
        val result = service.delete(id)

        return if (result) {
            ResponseEntity.ok("DELETED")
        } else {
            ResponseEntity.status(400).body("DELETE_FAILED")
        }
    }

    @PutMapping("/bid/{productId}")
    @ApiOperation(value = "상품 낙찰", notes = "상품 낙찰 PUT API")
    fun sell(@PathVariable productId: Long, request: HttpServletRequest) = ResponseEntity.ok(
        service.sell(productId)
    )
}