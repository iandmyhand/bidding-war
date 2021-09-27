package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GoodsInfoDto(
    @get:JsonProperty("goods_id")
    val goodsId: Long,

    @get:JsonProperty("goods_name")
    val goodsName: String,

    @get:JsonProperty("goods_price")
    val goodsPrice: Int,

    @get:JsonProperty("goods_content")
    val goodsContent: String,

    @get:JsonProperty("goods_category")
    val goodsCatetory: String
)