package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.Bidding

data class BiddingResponse(
    val id: Long,
    val userId: Long,
    val productId: Long,
    val price: Long
) {
    companion object {
        fun of(bidding: Bidding): BiddingResponse {
            return BiddingResponse(
                id = bidding.id!!,
                userId = bidding.user.id!!,
                productId = bidding.user.id!!,
                price = bidding.price
            )
        }

        fun of(biddingList: List<Bidding>): List<BiddingResponse> {
            return biddingList.map { of(it) }
        }
    }
}
