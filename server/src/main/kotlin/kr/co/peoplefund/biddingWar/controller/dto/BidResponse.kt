package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Bid


class BidResponse (
    val id: Long,
    val productId: Long?,
    val userId: Long?,
    val biddingPrice: Long
) {
    companion object {
        fun of(bid: Bid): BidResponse {
            return BidResponse(
                id = bid.id!!,
                productId = bid.product.id,
                userId = bid.bidder.id,
                biddingPrice = bid.biddingPrice
            )
        }

        fun of(bids: List<Bid>): List<BidResponse> {
            return bids.map { of(it) }
        }
    }
}