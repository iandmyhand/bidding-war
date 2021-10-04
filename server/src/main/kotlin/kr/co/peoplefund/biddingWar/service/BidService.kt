package kr.co.peoplefund.biddingWar.service

import kr.co.peoplefund.biddingWar.controller.dto.BidRequest
import kr.co.peoplefund.biddingWar.controller.dto.BidResponse
import kr.co.peoplefund.biddingWar.domain.User
import kr.co.peoplefund.biddingWar.domain.repository.BidRepository
import kr.co.peoplefund.biddingWar.domain.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class BidService(val productRepository: ProductRepository, val bidRepository: BidRepository) {

    fun register(bidder: User, productId: Long, request: BidRequest): Long {
        val product = productRepository.findById(productId).orElseThrow()
        if (product.owner == bidder) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Only owner has authority for this function.")
        }
        val bid = request.toBid(bidder, product)
        return bidRepository.save(bid).id!!
    }

    fun list(productId: Long): List<BidResponse> {
        return BidResponse.of(bidRepository.findByProductId(productId))
    }

    fun finish(user: User, productId: Long) {
        val product = productRepository.findById(productId).orElseThrow()
        if (product.owner != user) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Only owner has authority for this function.")
        }
        if (product.winningBid != null) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Already finished.")
        }
        val bid = bidRepository.findFirstByProductIdOrderByBiddingPriceDesc(productId)
        product.winningBid = bid
        productRepository.save(product)
    }

}