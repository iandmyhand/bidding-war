package com.example.biddingwar.repository

import com.example.biddingwar.database.Bid
import org.springframework.data.repository.CrudRepository

interface BidRepository: CrudRepository<Bid, Long> {
    fun findByUserId(userId: Long): List<Bid>?
}