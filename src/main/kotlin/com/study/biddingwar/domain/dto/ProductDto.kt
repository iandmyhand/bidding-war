package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.biddingwar.common.DecryptRsa

data class ProductDto(

    @field:DecryptRsa
    @get:JsonProperty("product_name")
    val productName:String,
    @get:JsonProperty("product_group")
    val productGroup:String,
    @get:JsonProperty("product_desc")
    val productDesc:String,
    @get:JsonProperty("product_price")
    val productPrice:Int
)
