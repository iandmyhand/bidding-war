package com.example.biddingwar.service

import com.example.biddingwar.BiddingWarRepository
import com.example.biddingwar.BiddingWarService
import com.example.biddingwar.Product
import io.kotlintest.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class ServiceTest @Autowired constructor(repository: BiddingWarRepository, ) {

    val service: BiddingWarService = BiddingWarService(repository)

    @Test
    fun canaryTest() {
        true shouldBe true
    }

    @Test
    fun getAll() {
        // Given
        val appleWatch = Product(id = 1L, name = "애플워치", price = 399_000, description = "애플워치 SE")
        val ipad = Product(id = 2L, name = "아이패드", price = 539_000, description = "다음엔 아이패드")

        service.save(appleWatch)
        service.save(ipad)


        // When
        val result = service.getAll()

        // Then
        assertThat(result).hasSize(2)
        assertThat(result).first().isEqualTo(Product(id = 1L, name = "애플워치", price = 399_000, description = "애플워치 SE"))
        assertThat(result).last().isEqualTo(Product(id = 2L, name = "아이패드", price = 539_000, description = "다음엔 아이패드"))

    }

    @Test
    fun getById() {
        // Given
        val appleWatch = Product(id = 1L, name = "애플워치", price = 399_000, description = "애플워치 SE")
        service.save(appleWatch)

        // When
        val result = service.get(id = 1L).get()

        // Then
        assertThat(result).isEqualTo(Product(id=1L, name="애플워치", price=399000, description="애플워치 SE"))
    }

    @Test
    fun saveProduct() {
        // Given
        val appleWatch = Product(id = 1L, name = "애플워치", price = 399_000, description = "애플워치 SE")

        // When
        service.save(appleWatch)

        // Then
        val savedProduct: Product = service.get(id = 1).get()

        assertThat(savedProduct).isEqualTo(Product(id=1L, name="애플워치", price=399000, description="애플워치 SE"))
    }
}
