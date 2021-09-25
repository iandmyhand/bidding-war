package com.study.peoplefund.domain.repository

import com.study.peoplefund.domain.Bidding
import org.springframework.data.jpa.repository.JpaRepository

interface BiddingRepository : JpaRepository<Bidding, Long?> {
    fun findByProductId(productId: Long): List<Bidding>

    fun existsByPriceGreaterThanEqual(price: Long): Boolean
}