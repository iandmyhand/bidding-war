package kr.co.peoplefund.biddingWar.service

import kr.co.peoplefund.biddingWar.controller.dto.BidRequest
import kr.co.peoplefund.biddingWar.controller.dto.BidResponse
import kr.co.peoplefund.biddingWar.domain.User
import kr.co.peoplefund.biddingWar.domain.repository.BidRepository
import kr.co.peoplefund.biddingWar.domain.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class BidService(val productRepository: ProductRepository, val bidRepository: BidRepository) {

    fun register(bidder: User, productId: Long, request: BidRequest): Long {
        val product = productRepository
            .findById(productId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.") }
        if (product.owner.id == bidder.id) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Owner has not an authority for this function.")
        }
        if (product.winningBid != null) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Already finished bid.")
        }
        val bid = request.toBid(bidder, product)
        if (bid.biddingPrice <= 0) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Put the positive number.")
        }
        val isBidExist = bidRepository.existsByProductId(productId)
        if (isBidExist) {
            val highestBid = bidRepository.findFirstByProductIdOrderByBiddingPriceDesc(productId)
            if (bid.biddingPrice <= highestBid.biddingPrice) {
                throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Put the greater price than registered bids.")
            }
        }
        return bidRepository.save(bid).id!!
    }

    fun list(productId: Long): List<BidResponse> {
        return BidResponse.of(bidRepository.findByProductId(productId))
    }

    fun finish(user: User, productId: Long) {
        val product = productRepository
            .findById(productId)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.") }
        if (product.owner.id != user.id) {
            println("Finishing bids - Owner: ${product.owner.id}, User: ${user.id}")
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Only owner has authority for this function.")
        }
        val isBidExist = bidRepository.existsByProductId(productId)
        if (!isBidExist) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "There is not a bid yet.")
        }
        if (product.winningBid != null) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Already finished.")
        }
        val winningBid = bidRepository.findFirstByProductIdOrderByBiddingPriceDesc(productId)
        product.winningBid = winningBid
        productRepository.save(product)
    }

    @Scheduled(cron = "0 * * * * *")
    fun checkBiddingEnds() {
        val now = Date()
        val products = productRepository.findByWinningBidIsNullAndBidsExistsBiddingEndTimeLessThanEqual(now)
        println("입찰을 종료합니다: ${products.size}건, $now")
        products.map {
            it.id?.let { productId -> finish(it.owner, productId) }
        }
    }

}