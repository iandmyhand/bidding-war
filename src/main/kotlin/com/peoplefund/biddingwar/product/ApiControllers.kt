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
    fun createProduct(@RequestBody @Valid productDto: ProductDto): ResponseEntity<Any> {
        val product: Product = productDto.of()
        productRepository.save(product)
        return ResponseEntity
            .created(URI.create("/api/products/" + product.id))
            .build()
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: Long): ResponseEntity<ProductDto> {
        val findProduct = productRepository
            .findById(id)
            .orElseThrow { NoRegisteredProduct() }

        return ResponseEntity
            .ok()
            .body(ProductDto(findProduct))
    }

    @GetMapping
    fun list(@ModelAttribute searchProduct: SearchProductDto): ResponseEntity<List<ProductDto>> {
        val findProducts = productService.list(searchProduct)

        return ResponseEntity
            .ok()
            .body(findProducts.map { product ->
                ProductDto(product)
            })
    }

    @ExceptionHandler(NoRegisteredProduct::class)
    fun handleIllegalArgsException(e: NoRegisteredProduct?): ResponseEntity<*>? {
        return ResponseEntity.badRequest().build<Any>()
    }

}
