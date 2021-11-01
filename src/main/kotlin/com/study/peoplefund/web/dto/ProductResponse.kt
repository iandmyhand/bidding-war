package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.Product
import com.study.peoplefund.domain.User

data class ProductResponse(
        val firstOwnerId: Long? = null,
        val id: Long,
        val name: String,
        val minPrice: Long,
        val is_sold: Boolean
) {
    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                    firstOwnerId = product.firstOwnerId,
                    id = product.id!!,
                    name = product.name,
                    minPrice = product.minPrice,
                    is_sold = product.is_sold
            )
        }

        fun of(products: List<Product>): List<ProductResponse> {
            return products.map { of(it) }
        }
    }
}
