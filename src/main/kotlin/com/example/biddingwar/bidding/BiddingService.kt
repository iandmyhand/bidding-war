package com.example.biddingwar.bidding

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
@Transactional
class BiddingService(val repository: BiddingRepository){

    fun getAll() = repository.findAll()

    fun getBiddingByProduct(productId: Long) = repository.findByProductId(productId)

    fun save(bidding: Bidding) {
        if (bidding.account == bidding.product?.seller) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        }
        repository.save(bidding)
    }
}