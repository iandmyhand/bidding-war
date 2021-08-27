package kr.co.peoplefund.biddingWar.service

import kr.co.peoplefund.biddingWar.controller.dto.BidRequest
import kr.co.peoplefund.biddingWar.controller.dto.BidResponse
import kr.co.peoplefund.biddingWar.domain.repository.BidRepository
import org.springframework.stereotype.Service

@Service
class BidService(val bidRepository: BidRepository) {

    fun register(productId: Long, request: BidRequest): Long {
        val bid = request.toBid()
        bid.productId = productId
        return bidRepository.save(bid).id!!
    }

    fun list(productId: Long): List<BidResponse> {
        return BidResponse.of(bidRepository.findByProductId(productId))
    }

}