package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Bid
import kr.co.peoplefund.biddingWar.domain.Product
import java.util.*

class ProductResponse (
    val id: Long,
    val ownerUserId: Long,
    val name: String,
    val price: Long,
    val minimumBiddingPrice: Long,
    val bids: MutableList<Bid>,
    val winningBidId: Long?,
    val biddingEndTime: Date
) {
    companion object {
        fun of(product: Product): ProductResponse {
            println("product: ${product.id} / ${product.owner.id}")
            product.bids.map {println("bid: ${it.id} / ${it.biddingPrice}")}
            return ProductResponse(
                id = product.id!!,
                ownerUserId = product.owner.id!!,
                name = product.name,
                price = product.price,
                minimumBiddingPrice = product.minimumBiddingPrice,
                bids = product.bids,
                winningBidId = product.winningBid?.id,
                biddingEndTime = product.biddingEndTime
            )
        }

        fun of(products: List<Product>): List<ProductResponse> {
            return products.map { of(it) }
        }
    }
}