package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Bid
import kr.co.peoplefund.biddingWar.domain.Product
import kr.co.peoplefund.biddingWar.domain.User

class ProductRequest(
    val token: String,
    val name: String,
    val price: Long,
    val minimumBiddingPrice: Long
) {
    fun toProduct(user: User): Product {
        return Product(
            owner = user,
            name = this.name,
            price = this.price,
            minimumBiddingPrice = this.minimumBiddingPrice,
            mutableListOf<Bid>()
        )
    }
}