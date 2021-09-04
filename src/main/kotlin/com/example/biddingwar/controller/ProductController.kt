package com.example.biddingwar.controller

import com.example.biddingwar.database.Product
import com.example.biddingwar.service.product.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/products")
class ProductController(val service: ProductService) {
    @GetMapping
    fun products() = ResponseEntity.ok(service.getAll())

    @GetMapping("/{id}")
    fun product(@PathVariable id: Long) = service.get(id)

    @PostMapping
    fun create(@RequestBody product: Product, request: HttpServletRequest) : ResponseEntity<String> {
        val result = service.save(product, request)

        return if (result) {
            ResponseEntity.ok(
                "PID: ${product.id} (${product.name} 이 등록 되었습니다."
            )
        } else {
            ResponseEntity.status(404).body("상품 등록에 실패했습니다.")
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = if (service.delete(id)) {
        ResponseEntity.ok("PID: ${id}가 삭제 되었습니다.")
    } else {
        ResponseEntity.status(404).body("존재하지 않는 PID: ${id} 입니다.")
    }
}