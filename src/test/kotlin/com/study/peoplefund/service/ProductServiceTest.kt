package com.study.peoplefund.service

import com.study.peoplefund.domain.Product
import com.study.peoplefund.domain.repository.BiddingRepository
import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.domain.repository.SessionRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.helper.AuthHelper
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ProductServiceTest @Autowired constructor(
    val productRepository: ProductRepository,
    biddingRepository: BiddingRepository,
    userRepository: UserRepository,
    sessionRepository: SessionRepository
) : AuthHelper(userRepository, sessionRepository) {
    val productService = ProductService(productRepository, biddingRepository, userRepository)

    @Test
    fun `상품 등록`() {
        val sellerId = createTestUser()

        val request = ProductRequest(
            name = "담보 채권",
            price = 100_000_000L
        )

        val id = productService.register(request, sellerId)
        val created = productRepository.findAll().first()

        assertAll(
            {
                assertThat(id).isEqualTo(created.id)
            },
            {
                assertThat(created.seller.id).isEqualTo(sellerId)
            },
            {
                assertThat(created)
                    .extracting("name", "minPrice")
                    .isEqualTo(listOf("담보 채권", 100_000_000L))
            }
        )
    }

    @Test
    fun `단건 조회`() {
        val sellerId = createTestUser()

        val id = productRepository.save(Product(
            seller = userRepository.getById(sellerId),
            name = "개인 채권",
            minPrice = 10_000_000L
        )).id!!

        val detail = productService.detail(id)
        assertThat(detail).isEqualTo(
            ProductResponse(id = id, sellerId = sellerId, name = "개인 채권", minPrice = 10_000_000L)
        )
    }

    @Test
    fun `목록 조회`() {
        val sellerId = createTestUser()
        val seller = userRepository.getById(sellerId)

        productRepository.save(Product(seller = seller, name = "담보 채권", minPrice = 100_000_000L))
        productRepository.save(Product(seller = seller, name = "개인 채권", minPrice = 100_000_00L))

        val list = productService.list()
        assertThat(list).hasSize(2)
    }
}