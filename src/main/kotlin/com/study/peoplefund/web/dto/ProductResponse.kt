package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.Product

data class ProductResponse(
        val id: Long,
        val name: String,
        val minPrice: Long,
        val currentPrice: Long
) {
    companion object {
        fun of(product: Product): ProductResponse {
            return ProductResponse(
                    id = product.id!!,
                    name = product.name,
                    minPrice = product.minPrice,
                    currentPrice = product.currentPrice
            )
        }

        fun of(products: List<Product>): List<ProductResponse> {
            return products.map { of(it) }
        }
    }
}
