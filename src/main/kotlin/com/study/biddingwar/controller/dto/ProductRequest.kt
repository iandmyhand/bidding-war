package com.study.biddingwar.controller.dto

import com.study.biddingwar.domain.Product

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
