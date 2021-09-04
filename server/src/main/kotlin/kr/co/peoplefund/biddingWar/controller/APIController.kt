package kr.co.peoplefund.biddingWar.controller

import kr.co.peoplefund.biddingWar.controller.dto.*
import kr.co.peoplefund.biddingWar.domain.User
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
        val session = userService.validateToken(token)
        val user = session.userId?.let { userService.getUser(it) }
        val id = user?.let { productService.register(it, request) }
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
        val session = userService.validateToken(token)
        val user = session.userId?.let { userService.getUser(it) }
        val id = user?.let { bidService.register(it, productId, request) }
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
        val user1 = createUser("a", "a")
        val user2 = createUser("b", "b")
        println("${user1.id} / ${user1.email} / ${user1.password}")
        println("${user2.id} / ${user2.email} / ${user2.password}")

        val loginRequest1 = LoginRequest("a", "a")
        val loginResponse1 = userService.login(loginRequest1)
        val loginRequest2 = LoginRequest("b", "b")
        val loginResponse2 = userService.login(loginRequest2)
        val productRequest = ProductRequest(loginResponse1.sessionKey, "test", 10, 5)
        val productId = productService.register(user1, productRequest)
        val bidRequest = BidRequest(loginResponse2.sessionKey, 7)
        bidRequest?.let {bidService.register(user2, productId, bidRequest)}

        val products = productService.list()
        val hackResponse = user1.id?.let { HackResponse(loginResponse1.sessionKey, it, products) }
        return ResponseEntity.ok(hackResponse)
    }

    private fun createUser(email: String, password: String): User {
        val userRequest = UserRequest(email, password)
        val userId = userService.register(userRequest)
        return userService.getUser(userId)
    }

}
