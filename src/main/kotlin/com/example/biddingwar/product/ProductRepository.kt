package com.example.biddingwar.product

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ProductRepository : JpaRepository<Product, Long> {
    fun findByBiddingEndDateTimeLessThanEqual(biddingEndDateTime: LocalDateTime): List<Product>
}