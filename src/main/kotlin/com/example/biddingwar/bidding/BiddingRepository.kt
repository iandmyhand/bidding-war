package com.example.biddingwar.bidding

import org.springframework.data.repository.CrudRepository

interface BiddingRepository : CrudRepository<Bidding, Long> {
    fun findByProductId(productId: Long): List<Bidding>?

    fun findHighestBiddingByProductId(productId: Long) : Bidding? {
        val biddings = findByProductId(productId)
        return biddings?.maxByOrNull { it -> it.price }
    }
}