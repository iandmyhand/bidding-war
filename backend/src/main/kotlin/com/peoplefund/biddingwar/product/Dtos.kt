package com.peoplefund.biddingwar.product

import org.hibernate.validator.constraints.Range
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ProductCreateRequest(
    @NotBlank val name: String,
    @Range(min=0, max=1_000_000_000) val amount: Long,
    var description: String? = null,
) {
    fun of(): Product = Product(this.name, this.amount, this.description)
}

data class ProductResponse(
    @NotNull val id: Long?,
    @NotBlank val name: String,
    @Range(min=0, max=1_000_000_000) val amount: Long,
    var description: String? = null,
) {
    constructor(product: Product) : this(product.id, product.name, product.amount, product.description)
}

data class ProductSearch(
    val name: String?,
    // TODO : 이후 범위 검색 추가
//    @Range(min=0, max=1_000_000_000) val minAmount: Long?,
//    @Range(min=0, max=1_000_000_000) val maxAmount: Long?,
)

data class UserSignupRequest(
    @Size(min=5, max=30) val userId: String,
    @Size(min=5, max=20) val password: String
)

data class UserSigninResponse(
    @Size(min=5, max=30) val userId: String,
) {
    constructor(user: User) : this(user.userId)
}