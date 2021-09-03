package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Bid
import kr.co.peoplefund.biddingWar.domain.Product

class ProductResponse (
    val id: Long,
    val name: String,
    val price: Long,
    val minimumBiddingPrice: Long,
    val bids: MutableList<Bid>
) {
    companion object {
        fun of(product: Product): ProductResponse {
            println("product: ${product.id} / ${product.owner.id}")
            product.bids.map {println("bid: ${it.id} / ${it.biddingPrice}")}
            val res = ProductResponse(
                id = product.id!!,
                name = product.name,
                price = product.price,
                minimumBiddingPrice = product.minimumBiddingPrice,
                bids = product.bids
            )
            if (res.bids.size > 0) {println(res.bids[0].id)}

            return res
        }

        fun of(products: List<Product>): List<ProductResponse> {
            return products.map { of(it) }
        }
    }
}