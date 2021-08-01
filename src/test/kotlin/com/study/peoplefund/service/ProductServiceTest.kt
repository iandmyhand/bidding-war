package com.study.peoplefund.service

import com.study.peoplefund.domain.Product
import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ProductServiceTest @Autowired constructor(
        val productRepository: ProductRepository,
) {
    val productService = ProductService(productRepository)

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun `상품 등록`() {
        val request = ProductRequest(
                name = "담보 채권",
                price = 100_000_000L
        )

        val id = productService.register(request)
        val created = productRepository.findAll().first()

        assertAll(
                {
                    assertThat(id).isEqualTo(created.id)
                },
                {
                    assertThat(created)
                            .extracting("name", "price")
                            .isEqualTo(listOf("담보 채권", 100_000_000L))
                }
        )
    }

    @Test
    fun `단건 조회`() {
        val id = productRepository.save(Product(name = "개인 채권", 10_000_000L)).id!!

        val detail = productService.detail(id)
        assertThat(detail).isEqualTo(
                ProductResponse(id = id, name = "개인 채권", price = 10_000_000L)
        )
    }

    @Test
    fun `목록 조회`() {
        productRepository.save(Product(name = "담보 채권", 100_000_000L))
        productRepository.save(Product(name = "개인 채권", 100_000_00L))

        val list = productService.list()
        assertThat(list).hasSize(2)
    }
}