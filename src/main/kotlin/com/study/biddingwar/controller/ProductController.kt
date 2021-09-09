package com.study.biddingwar.controller

import com.study.biddingwar.common.DecryptRsa
import com.study.biddingwar.common.crypto.CryptoRsaUtils
import com.study.biddingwar.domain.RsaKeyStore
import com.study.biddingwar.domain.dto.ProductDto
import com.study.biddingwar.domain.dto.ProductResultDto
import com.study.biddingwar.service.ProductService
import com.study.biddingwar.service.SecuritySupportService
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.PrivateKey

@RestController
@RequestMapping("/v1")
class ProductController(private val productService: ProductService,
                        private val securitySupportService: SecuritySupportService) {

    @GetMapping("/products")
    fun getProducts(@RequestParam(name="page")cPage:Int,
                    @RequestParam(name="size")size:Int,
                    @RequestParam(name="search_type")searchType:String?
    ): ResponseEntity<List<ProductResultDto>> {
        var page = if(cPage == 1) 0 else cPage
        val products= productService.getProducts(PageRequest.of(page, size), searchType)
        return ResponseEntity.ok().body(products)
    }

    @GetMapping("/products/{id}")
    fun getProduct(@PathVariable(name = "id") id:Long): ResponseEntity<ProductResultDto> {
        val product =  productService.getProduct(id)
        return ResponseEntity.ok().body(product)
    }

    @PostMapping("/product")
    fun createProduct(@RequestBody productDto: ProductDto): ResponseEntity<ProductResultDto> {
        val product = productService.createProduct(productDto)

        return ResponseEntity.ok().body(product)
    }

    @GetMapping("/product")
    fun createProductTest(@RequestParam("msg")@DecryptRsa message:String): ResponseEntity<String> {
        return ResponseEntity.ok().body(message)
    }

    @PutMapping("/products/{id}")
    fun modifyProduct(@PathVariable(name="id") id:Long,
                      @RequestBody productDto:ProductDto): ResponseEntity<ProductResultDto> {
        val product = productService.modifyProduct(id, productDto)
        return ResponseEntity.ok().body(product)
    }

    @DeleteMapping("/products/{id}")
    fun deleteProduct(@PathVariable(name="id") id:Long): ResponseEntity<ProductResultDto> {
        val product = productService.deleteProduct(id)
        return ResponseEntity.ok().body(product)
    }
}