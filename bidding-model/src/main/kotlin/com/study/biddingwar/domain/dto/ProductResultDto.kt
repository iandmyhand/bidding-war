package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class ProductResultDto(
    val id:Long,
    @get:JsonProperty("product_group")
    val productGroup:String,
    @get:JsonProperty("product_name")
    val productName:String,
    @get:JsonProperty("product_desc")
    val productDesc: String?,
    @get:JsonProperty("product_price")
    val productPrice:Int,
    @get:JsonIgnore
    val userId:Long,
    @get:JsonProperty("bid_status")
    val bidStatus:String

)