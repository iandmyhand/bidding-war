package com.example.biddingwar.repository

import com.example.biddingwar.database.entity.Bid
import org.springframework.data.repository.CrudRepository

interface BidRepository: CrudRepository<Bid, Long> {
    fun findByUserId(userId: Long): List<Bid>?

    fun findByProductId(productId: Long): List<Bid>?

    fun findFirstByProductIdOrderByBiddingPriceDesc(productId: Long): Bid?
}