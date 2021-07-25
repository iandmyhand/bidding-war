package com.peoplefund.biddingwar.product

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank

data class ProductDto(
    @NotBlank val name: String,
    @Range(min=0, max=1_000_000_000) val amount: Long,
    var description: String? = null,
) {
    constructor(product: Product) : this(product.name, product.amount, product.description)

    fun of(): Product = Product(this.name, this.amount, this.description)
}

data class SearchProductDto(
    val name: String?,
    // TODO : 범위 검색 추가
//    @Range(min=0, max=1_000_000_000) val minAmount: Long?,
//    @Range(min=0, max=1_000_000_000) val maxAmount: Long?,
)
