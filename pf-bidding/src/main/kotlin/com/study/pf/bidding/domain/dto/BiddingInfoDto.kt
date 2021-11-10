package com.study.pf.bidding.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.study.pf.bidding.domain.entity.Goods
import com.study.pf.bidding.domain.entity.User

data class BiddingInfoDto(
        @get:JsonProperty("bidding_id")
        val biddingId: Long,

        @get:JsonProperty("bidding_goods")
        val biddingGoods: Goods,

        @get:JsonProperty("bidding_user")
        val biddingUser: User,

        @get:JsonProperty("bidding_price")
        val biddingPrice: Int
)