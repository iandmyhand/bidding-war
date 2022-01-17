package com.example.biddingwar.repository

import com.example.biddingwar.database.Bid
import io.kotlintest.shouldBe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class BidRepositoryTest @Autowired constructor(bidRepository: BidRepository) {

    val repository:BidRepository = bidRepository

    @Test
    fun canaryTest() {
        true shouldBe true
    }

    @Test
    fun `최대 입찰가 가져오기`() {
        true shouldBe true

        // Repository 테스트 하기
        val minBid = Bid(id=1L, productId = 1L, userId = 1L, biddingPrice = 1_000)
        val maxBid = Bid(id=2L, productId = 1L, userId = 1L, biddingPrice = 4_000)

        val firstBid = repository.findFirstByProductIdOrderByBiddingPriceDesc(1L)

        if (firstBid != null) {
            assertThat(firstBid.id).isEqualTo(maxBid.id)
        }
    }
}