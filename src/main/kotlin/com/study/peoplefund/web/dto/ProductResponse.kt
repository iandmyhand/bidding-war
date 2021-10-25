package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.Product

data class ProductResponse(
        val firstOwnerId: Long? = null,
        val id: Long,
        val name: String,
        val minPrice: Long
) {
    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                    firstOwnerId = product.firstOwnerId,
                    id = product.id!!,
                    name = product.name,
                    minPrice = product.minPrice
            )
        }

        fun of(products: List<Product>): List<ProductResponse> {
            return products.map { of(it) }
        }
    }
}
