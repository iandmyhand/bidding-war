package com.peoplefund.biddingwar.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/api/products")
class ProductController(
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val productService: ProductService,
) {

    @PostMapping
    fun createProduct(@RequestBody @Valid productCreateRequest: ProductCreateRequest): ResponseEntity<Any> {
        val product: Product = productCreateRequest.of()
        productRepository.save(product)
        return ResponseEntity
            .created(URI.create("/api/products/" + product.id))
            .build()
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        val findProduct = productRepository
            .findById(id)
            .orElseThrow { NoRegisteredProduct() }

        return ResponseEntity
            .ok()
            .body(ProductResponse(findProduct))
    }

    @GetMapping
    fun list(@ModelAttribute searchProduct: SearchProductDto): ResponseEntity<List<ProductResponse>> {
        val findProducts = productService.list(searchProduct)

        return ResponseEntity
            .ok()
            .body(findProducts.map { product ->
                ProductResponse(product)
            })
    }

    @ExceptionHandler(NoRegisteredProduct::class)
    fun handleIllegalArgsException(e: NoRegisteredProduct?): ResponseEntity<*>? {
        return ResponseEntity.badRequest().build<Any>()
    }

}
