package com.example.biddingwar.repository

import com.example.biddingwar.entity.Bid
import org.springframework.data.jpa.repository.JpaRepository

interface BidRepository : JpaRepository<Bid, Long> {
    fun findAllByItemId(itemId: Long) : Iterable<Bid>?
    fun findFirstByItemIdOrderByPriceDesc(itemId: Long): Bid
}