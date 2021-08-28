package com.example.biddingwar.bidding

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BiddingService(val repository: BiddingRepository){

    fun getAll() = repository.findAll()

//    TODO: 상품아이디 별로 가져오도록
    fun getBiddingByProduct(id: Long) = repository.findById(id)

    fun save(bidding: Bidding) = repository.save(bidding)
}