package com.example.biddingwar

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/bidding-war")

class BiddingWarController(val service: BiddingWarService) {

    @GetMapping
    fun products() = ResponseEntity.ok(service.getAll())

    @GetMapping("/{id}")
    fun product(@PathVariable id: Long) = service.get(id)

    @PostMapping
    fun create(@RequestBody product: Product) : ResponseEntity<String> {
        val result = service.save(product)

        return ResponseEntity.ok(
            "PID: ${product.id} (${product.name})이 등록 되었습니다."
        )
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = if (service.delete(id)) {
        ResponseEntity.ok("PID: ${id}가 삭제 되었습니다.")
    } else {
        ResponseEntity.status(404).body("존재하지 않는 PID: ${id} 입니다.")
    }

}