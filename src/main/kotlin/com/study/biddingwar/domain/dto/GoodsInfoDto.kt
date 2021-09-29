package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

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
    val goodsCatetory: String,

    @get:JsonProperty("create_date")
    val createDate: Instant?
)