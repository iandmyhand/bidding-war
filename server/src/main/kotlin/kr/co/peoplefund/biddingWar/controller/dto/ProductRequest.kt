package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Product

class ProductRequest(
    val token: String,
    val name: String,
    val price: Long
) {
    fun toProduct(): Product {
        return Product(
            name = this.name,
            price = this.price
        )
    }
}