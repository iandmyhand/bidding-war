package com.peoplefund.biddingwar.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class RepositoriesTests @Autowired constructor(val productRepository: ProductRepository, ) {
    @Test
    internal fun `상품 등록`() {
        val newProduct = Product("주담대 1-1", 50_000_000)
        productRepository.save(newProduct)

        val findProduct = productRepository.findById(newProduct.id!!).get()

        assertThat(findProduct.id).isEqualTo(newProduct.id)
        assertThat(findProduct.name).isEqualTo(newProduct.name)
        assertThat(findProduct.amount).isEqualTo(newProduct.amount)
    }

}
