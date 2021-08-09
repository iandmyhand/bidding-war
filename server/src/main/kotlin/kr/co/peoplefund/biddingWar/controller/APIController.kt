package kr.co.peoplefund.biddingWar.controller

import kr.co.peoplefund.biddingWar.controller.dto.ProductRequest
import kr.co.peoplefund.biddingWar.controller.dto.ProductResponse
import kr.co.peoplefund.biddingWar.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@CrossOrigin(origins = ["http://localhost:8080", "http://localhost:3000"])
@RequestMapping("/api/products")
class APIController(val productService: ProductService) {

    @PostMapping
    fun apiRegister(@RequestBody request: ProductRequest): ResponseEntity<Void> {
        val id = productService.register(request)
        return ResponseEntity.created(URI.create("/api/products/$id")).build()
    }

    @GetMapping
    fun apiList(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.ok(productService.list())
    }

    @GetMapping("/{id}")
    fun apiDetail(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.detail(id))
    }

}