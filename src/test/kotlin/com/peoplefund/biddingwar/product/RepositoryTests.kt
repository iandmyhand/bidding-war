package com.peoplefund.biddingwar.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProductRepositoryTests(@Autowired val productRepository: ProductRepository) {

    @Test
    internal fun `상품 등록`() {
        val newProduct = Product("주담대 1", 50_000_000)
        productRepository.save(newProduct)

        val findProduct = productRepository.findById(newProduct.id!!).get()

        `상품 단건 검증`(findProduct, newProduct)
    }

    @Test
    internal fun `상품 단건 조회`() {
        val newProduct = Product("주담대 1", 50_000_000)

        productRepository.save(newProduct)

        val product = productRepository.findById(newProduct.id!!).get()

        `상품 단건 검증`(product, newProduct)
    }

    @Test
    internal fun `상품 목록 조회`() {
        val newProduct1 = Product("주담대 1", 50_000_000)
        val newProduct2 = Product("주담대 3", 15_000_000)
        val newProduct3 = Product("개신대 1", 30_000_000)

        productRepository.saveAll(listOf(newProduct1, newProduct2, newProduct3))

        val products = productRepository.findByNameContains("주담대")

        assertThat(products.count()).isEqualTo(2);
    }

    private fun `상품 단건 검증`(
        product: Product,
        expectedProduct: Product
    ) {
        assertThat(product.id).isEqualTo(expectedProduct.id)
        assertThat(product.name).isEqualTo(expectedProduct.name)
        assertThat(product.amount).isEqualTo(expectedProduct.amount)
    }
}
