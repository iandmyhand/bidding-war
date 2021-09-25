package com.example.biddingwar.service.bid

import com.example.biddingwar.database.Bid
import com.example.biddingwar.database.Product
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ProductRepository
import com.example.biddingwar.service.product.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional


@Service
@Transactional
class BidService(val repository: BidRepository) {
    fun getAll() : ResponseEntity<MutableIterable<Bid>> = ResponseEntity.ok().body(repository.findAll())

    fun getBidByUserId(userId: Long): List<Bid>? = repository.findByUserId(userId)

    fun getByProductId(productId: Long): List<Bid>? = repository.findByProductId(productId)

    fun saveBid(bid: Bid, request: HttpServletRequest): ResponseEntity<Bid> {
        repository.save(bid)
        return ResponseEntity.ok().body(bid)
    }
}
