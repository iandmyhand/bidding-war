package kr.co.peoplefund.biddingWar.controller

import kr.co.peoplefund.biddingWar.controller.dto.*
import kr.co.peoplefund.biddingWar.service.BidService
import kr.co.peoplefund.biddingWar.service.ProductService
import kr.co.peoplefund.biddingWar.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:3000"])
@RequestMapping("/api")
class APIController(val userService: UserService, val productService: ProductService, val bidService: BidService) {

    @PostMapping("/products")
    fun apiRegisterProduct(@RequestBody request: ProductRequest): ResponseEntity<Void> {
        val token = request.token
        userService.validateToken(token)

        val id = productService.register(request)
        return ResponseEntity.created(URI.create("/api/products/$id")).build()
    }

    @GetMapping("/products")
    fun apiListProducts(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.ok(productService.list())
    }

    @GetMapping("/products/{id}")
    fun apiDetailProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.detail(id))
    }

    @PostMapping("/products/{productId}/bids")
    fun apiRegisterBid(@PathVariable productId: Long, @RequestBody request: BidRequest): ResponseEntity<Void> {
        val token = request.token
        userService.validateToken(token)

        val id = bidService.register(productId, request)
        return ResponseEntity.created(URI.create("/api/products$productId/bids/$id")).build()
    }

    @GetMapping("/products/{productId}/bids")
    fun apiListBids(@PathVariable productId: Long): ResponseEntity<List<BidResponse>> {
        return ResponseEntity.ok(bidService.list(productId))
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

    @GetMapping("/hack")
    fun apiHack(): ResponseEntity<HackResponse> {
        val email = "a"
        val password = "a"
        val userRequest = UserRequest(email, password)
        val userId = userService.register(userRequest)
        val loginRequest = LoginRequest(email, password)
        val loginResponse = userService.login(loginRequest)
        val productRequest = ProductRequest(loginResponse.sessionKey, "test", 10, 5)
        productService.register(productRequest)
        val products = productService.list()
        val hackResponse = HackResponse(loginResponse.sessionKey, userId, products)
        return ResponseEntity.ok(hackResponse)
    }

}