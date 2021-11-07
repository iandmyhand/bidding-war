package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class ProductDto(

    @get:JsonProperty("product_name")
    var productName:String,
    @get:JsonProperty("product_group")
    var productGroup:String,
    @get:JsonProperty("product_desc")
    var productDesc:String,
    @get:JsonProperty("product_price")
    var productPrice:Int
)
