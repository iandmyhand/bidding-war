package kr.co.peoplefund.biddingWar.controller

import kr.co.peoplefund.biddingWar.controller.dto.ProductRequest
import kr.co.peoplefund.biddingWar.controller.dto.ProductResponse
import kr.co.peoplefund.biddingWar.service.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping
class MainController(val productService: ProductService) {

    @PostMapping("/api/products")
    fun apiRegister(@RequestBody request: ProductRequest): ResponseEntity<Void> {
        val id = productService.register(request)
        return ResponseEntity.created(URI.create("/api/products/$id")).build()
    }

    @GetMapping("/api/products")
    fun apiList(): ResponseEntity<List<ProductResponse>> {
        return ResponseEntity.ok(productService.list())
    }

    @GetMapping("/api/products/{id}")
    fun apiDetail(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return ResponseEntity.ok(productService.detail(id))
    }

    @GetMapping("/api/products")
    fun list(model: Model): String {
        model["items"] = productService.list()
        return "/list"
    }
}