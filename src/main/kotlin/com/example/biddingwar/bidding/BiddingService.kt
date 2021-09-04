package com.example.biddingwar.bidding

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BiddingService(val repository: BiddingRepository){

    fun getAll() = repository.findAll()

    fun getBiddingByProduct(productId: Long) = repository.findByProductId(productId)

    fun save(bidding: Bidding) = repository.save(bidding)
}