package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Bid
import kr.co.peoplefund.biddingWar.domain.Product
import kr.co.peoplefund.biddingWar.domain.User
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

class ProductRequest(
    val token: String,
    val name: String,
    val price: Long,
    val minimumBiddingPrice: Long,
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val biddingEndTime: Date
) {
    fun toProduct(user: User): Product {
        return Product(
            owner = user,
            name = this.name,
            price = this.price,
            minimumBiddingPrice = this.minimumBiddingPrice,
            bids = mutableListOf<Bid>(),
            biddingEndTime = this.biddingEndTime
        )
    }
}