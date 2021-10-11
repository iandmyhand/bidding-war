package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.Product

data class ProductResponse(
    val id: Long,
    val sellerId: Long,
    val name: String,
    val minPrice: Long,
    val status: String
) {
    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id!!,
                sellerId = product.seller.id!!,
                name = product.name,
                minPrice = product.minPrice,
                status = product.status.getValue()
            )
        }

        fun of(products: List<Product>): List<ProductResponse> {
            return products.map { of(it) }
        }
    }
}
