package com.example.biddingwar

import org.springframework.data.repository.CrudRepository

interface BiddingWarRepository: CrudRepository<Product, Long>

interface BiddingWarUserRepository: CrudRepository<User, Long> {
    fun findByEmail(email: String?): User?
}

interface BiddingWarBidRepository: CrudRepository<Bid, Long> {
    fun findByUserId(userId: Long): List<Bid>?
}