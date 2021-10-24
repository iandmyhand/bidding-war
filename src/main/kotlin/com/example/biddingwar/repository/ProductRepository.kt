package com.example.biddingwar.repository

import com.example.biddingwar.database.Product
import org.springframework.data.repository.CrudRepository
import java.time.Instant

interface ProductRepository: CrudRepository<Product, Long> {
    fun findByIsBidCompleteFalseAndBiddingEndTimeLessThanEqual(biddingEndTime: Instant): List<Product>
}