package com.example.biddingwar

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ProductServiceTest @Autowired constructor(repository: ProductRepository){
    val service: ProductService = ProductService(repository)

    @Test
    fun getAllTest() {
        val product1 = Product(id = 1L, name = "product1", price = 1000, description = "product1")
        val product2 = Product(id = 2L, name = "product2", price = 2000, description = "product2")
        service.save(product1)
        service.save(product2)

        val result = service.getAll()
        assertThat(result).hasSize(2)
    }

    @Test
    fun getProductTest() {
        val product1 = Product(id = 1L, name = "product1", price = 1000, description = "product1")
        service.save(product1)
        val result = service.getProduct(id = 1L)
        assertThat(result).isNotEmpty()
    }

}