package com.example.biddingwar.service.bid

import com.example.biddingwar.database.Bid
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ProductRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional


@Service
@Transactional
class BidService(val bidRepository: BidRepository, val productRepository: ProductRepository) {
    fun getAll() = bidRepository.findAll()

    fun getUserBids(userId: Long): List<Bid>? {
        return bidRepository.findByUserId(userId)
    }

    fun saveBid(bid: Bid, request: HttpServletRequest): Boolean {
        val product = productRepository.findById(bid.productId).get()
        bidRepository.save(bid)
        return true
    }
}
