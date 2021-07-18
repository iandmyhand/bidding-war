package com.study.peoplefund.domain

import com.study.peoplefund.domain.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class ProductTest @Autowired constructor(
        val productRepository: ProductRepository
) {

    @BeforeEach
    fun setUp() {
    }

    @Test
    fun `상품 생성 테스트`() {
        val product = Product(
                name = "담보채권",
                price = 100_000_000
        )

        productRepository.save(product)

        val saved = productRepository.findAll().first()

        assertThat(saved)
                .extracting(Product::id, Product::name, Product::price)
                .isEqualTo(listOf(1L, "담보채권", 100_000_000L))
    }
}