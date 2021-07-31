package com.study.biddingwar

import org.springframework.web.bind.annotation.* 
import org.springframework.http.ResponseEntity

@RestController // REST API Controller 임을 Spring 에게 알려줌
@RequestMapping("/item") // localhost:8080/item 형태 요청 주소로 매핑
class ItemController(val service: ItemService) {

    @GetMapping
    fun list() = ResponseEntity.ok(service.list())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ResponseEntity.ok(service.get(id))

    @PostMapping
    fun create(@RequestBody item: Item): ResponseEntity<Item> {
        val newItem = service.save(item)
        return ResponseEntity.ok(newItem)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = if (service.delete(id)) {
      ResponseEntity.ok("Item(id: $id) deleted")
    } else {
      ResponseEntity.status(404).body("Item(id: $id) not found")
    }
}