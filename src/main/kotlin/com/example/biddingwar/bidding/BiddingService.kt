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

    fun getHighestBiddingByProduct(productId: Long) : Bidding? {
        return repository.findHighestBiddingByProductId(productId)
    }

    fun biddingFinished(productId: Long): Boolean {
        val biddings = repository.findByProductId(productId)
        if (biddings == null) return false
        return biddings.any { it -> it.is_selected }
    }

    fun save(bidding: Bidding) {
        // 본인 상품에 입찰 못하게 방지
        if (bidding.account == bidding.product?.seller) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE)
        }
        // TODO: getHighestBiddingByProduct, biddingFinished 호출위치 변경
        repository.save(bidding)
    }
}