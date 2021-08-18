package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Product

class ProductResponse (
    val id: Long,
    val name: String,
    val price: Long
) {
    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!,
                name = product.name,
                price = product.price
            )
        }

        fun of(products: List<Product>): List<ProductResponse> {
            return products.map { of(it) }
        }
    }
}