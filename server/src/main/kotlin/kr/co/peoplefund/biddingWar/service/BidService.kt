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
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not Acceptable")
        }
        val bid = request.toBid()
        bid.productId = productId
        return bidRepository.save(bid).id!!
    }

    fun list(productId: Long): List<BidResponse> {
        return BidResponse.of(bidRepository.findByProductId(productId))
    }

}