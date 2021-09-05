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
                minPrice = 100_000_000L
        )

        productRepository.save(product)

        val saved = productRepository.findAll().first()

        assertThat(saved)
                .extracting(Product::name, Product::minPrice)
                .isEqualTo(listOf("담보채권", 100_000_000L))
    }
}