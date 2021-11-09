package com.example.biddingwar.service

import com.example.biddingwar.database.Bid
import com.example.biddingwar.repository.ProductRepository
import com.example.biddingwar.service.product.ProductService
import com.example.biddingwar.database.Product
import com.example.biddingwar.repository.BidRepository
import io.kotlintest.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class Product @Autowired constructor(repository: ProductRepository, bidRepository: BidRepository) {

    val service = ProductService(repository, bidRepository)

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
        val savedProduct: Product = service.save(appleWatch)

        // Then
        assertThat(savedProduct).isEqualTo(Product(id=1L, name="애플워치", price=399000, description="애플워치 SE"))
    }

    @Test
    fun finishBid() {
        // Given
        val appleWatch = Product(id = 1L, name = "애플워치", price = 399_000, description = "애플워치 SE")
        service.save(appleWatch)

        val firstBid = Bid(id=3L, productId = appleWatch.id, userId = 1L, biddingPrice = 400_000)
        service.bidRepository.save(firstBid)

        val secondBid = Bid(id=4L, productId = appleWatch.id, userId = 2L, biddingPrice = 500_000)
        service.bidRepository.save(secondBid)

        service.finishBid()
    }
}
