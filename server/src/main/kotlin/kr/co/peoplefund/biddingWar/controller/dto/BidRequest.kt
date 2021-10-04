package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Bid
import kr.co.peoplefund.biddingWar.domain.Product
import kr.co.peoplefund.biddingWar.domain.User

class BidRequest(
    val token: String,
    val biddingPrice: Long
) {
    fun toBid(bidder: User, product: Product): Bid {
        return Bid(
            bidder = bidder,
            biddingPrice = this.biddingPrice,
            product = product
        )
    }
}
