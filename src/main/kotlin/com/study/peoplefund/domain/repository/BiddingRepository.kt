package com.study.peoplefund.domain.repository

import com.study.peoplefund.domain.Bidding
import com.study.peoplefund.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface BiddingRepository : JpaRepository<Bidding, Long?> {
    fun findByProductId(productId: Long): List<Bidding>

    fun existsByPriceGreaterThanEqualAndProduct(price: Long, product: Product): Boolean

    fun existsByProductId(productId: Long): Boolean
}