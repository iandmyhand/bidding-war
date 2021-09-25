package com.study.biddingwar.controller

import com.study.biddingwar.controller.dto.LoginRequest
import com.study.biddingwar.controller.dto.LoginResponse
import com.study.biddingwar.controller.dto.ProductRequest
import com.study.biddingwar.controller.dto.*
import com.study.biddingwar.service.ProductService
import com.study.biddingwar.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:3000"])
@RequestMapping("/api")
class APIController(val productService: ProductService, val userService: UserService) {

    @PostMapping("/products")
    fun apiRegisterProduct(@RequestBody request: ProductRequest): ResponseEntity<Void> {
        val token = request.token
        println(token)
        userService.validateToken(token)

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

    @PostMapping("/auth/login")
    fun apiLogin(@RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.ok(userService.login(request))
    }

}
