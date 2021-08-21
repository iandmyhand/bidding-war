package kr.co.peoplefund.biddingWar

import kr.co.peoplefund.biddingWar.controller.dto.ProductRequest
import kr.co.peoplefund.biddingWar.controller.dto.ProductResponse
import kr.co.peoplefund.biddingWar.domain.repository.ProductRepository
import kr.co.peoplefund.biddingWar.service.ProductService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BiddingWarAPITest @Autowired constructor(
    productRepository: ProductRepository,
)
{
    val productService = ProductService(productRepository)

    @Test
    fun `상품 단건 조회`() {

        val testProduct = ProductRequest(
            name = "아이폰", price = 1300000
        )

        productService.register(testProduct)
        val result = productService.detail(1L)
        assertThat(result)
            .extracting(
                ProductResponse::id,
                ProductResponse::name,
                ProductResponse::price,
            )
            .isEqualTo(
                listOf(
                    1L, "아이폰", 1300000L
                )
            )
    }

}