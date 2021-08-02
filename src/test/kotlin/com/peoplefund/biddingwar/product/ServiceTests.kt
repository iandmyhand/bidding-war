package com.peoplefund.biddingwar.product

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductServiceTests {

    @Test
    internal fun `상품 목록 조회`() {
        val productRepository: ProductRepository = mockk()
        every {
            productRepository.findByNameContains("주담대")
        } returns
                listOf(
                    Product("주담대 1", 50_000_000),
                    Product("주담대 3", 15_000_000)
                )

        val productService = ProductService(productRepository)

        val products = productService.list(ProductSearch("주담대"))

        assertThat(products.count()).isEqualTo(2);
    }

}
