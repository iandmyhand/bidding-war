package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Bid

class BidRequest(
    val token: String,
    val userId: Long,
    val biddingPrice: Long
) {
    fun toBid(): Bid {
        return Bid(
            userId = this.userId,
            biddingPrice = this.biddingPrice
        )
    }
}
