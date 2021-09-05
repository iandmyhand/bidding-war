package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.Product

data class ProductRequest(
        val name: String,
        val price: Long
) {
    fun toProduct(): Product {
        return Product(
                name = this.name,
                minPrice = this.price
        )
    }
}