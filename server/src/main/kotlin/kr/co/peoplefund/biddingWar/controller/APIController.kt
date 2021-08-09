package kr.co.peoplefund.biddingWar.controller

import kr.co.peoplefund.biddingWar.controller.dto.ProductRequest
import kr.co.peoplefund.biddingWar.controller.dto.ProductResponse
import kr.co.peoplefund.biddingWar.controller.dto.UserRequest
import kr.co.peoplefund.biddingWar.service.ProductService
import kr.co.peoplefund.biddingWar.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:3000"])
@RequestMapping("/api")
class APIController(val productService: ProductService, val userService: UserService) {

    @PostMapping("/products")
    fun apiRegisterProduct(@RequestBody request: ProductRequest): ResponseEntity<Void> {
        val id = productService.register(request)
        return ResponseEntity.created(URI.create("/api/products/$id")).build()
    }

    @GetMapping("/products")
    fun apiListProduct(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.ok(productService.list())
    }

    @GetMapping("/products/{id}")
    fun apiDetailProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.detail(id))
    }

    @PostMapping("/users")
    fun apiRegisterUser(@RequestBody request: UserRequest): ResponseEntity<Void> {
        val id = userService.register(request)
        return ResponseEntity.created(URI.create("/api/users/$id")).build()
    }

}