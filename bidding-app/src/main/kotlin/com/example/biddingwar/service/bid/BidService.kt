package com.example.biddingwar.service.bid

import com.example.biddingwar.database.entity.Bid
import com.example.biddingwar.repository.BidRepository
import com.example.biddingwar.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional


@Service
@Transactional
class BidService(val repository: BidRepository, val productRepository: ProductRepository) {
    fun getAll() : ResponseEntity<MutableIterable<Bid>> = ResponseEntity.ok().body(repository.findAll())

    fun getBidByUserId(userId: Long): List<Bid>? = repository.findByUserId(userId)

    fun getByProductId(productId: Long): List<Bid>? = repository.findByProductId(productId)

    fun saveBid(bid: Bid, request: HttpServletRequest): ResponseEntity<Bid> {

        if (bid.userId == request.session.getAttribute("session")){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Duplicate User Bid")
        }

        val productBids: List<Bid>? = repository.findByProductId(bid.productId)
        val minimumBid: Bid? = productBids?.minByOrNull { it.biddingPrice }

        if (minimumBid != null) {
            if (bid.biddingPrice < minimumBid.biddingPrice) {
                throw ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Bidding price must be greater than minimum bidding price"
                )
            }
        }

        val isBidComplete: Boolean = productRepository.findById(bid.productId).get().isBidComplete

        if (isBidComplete) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Bid is already completed")
        }
        repository.save(bid)
        return ResponseEntity.ok().body(bid)
    }
}
