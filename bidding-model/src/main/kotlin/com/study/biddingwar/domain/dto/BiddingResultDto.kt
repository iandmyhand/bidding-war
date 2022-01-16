package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class BiddingResultDto(@get:JsonProperty("bidding_id")
                            val biddingId:Long,
                            @get:JsonProperty("product_id")
                            val productId:Long,
                            @get:JsonProperty("product_name")
                            val productName:String,
                            @get:JsonProperty("user_id")
                            val userId:Long,
                            @get:JsonProperty("user_name")
                            val userName:String,
                            @get:JsonProperty("bidding_price")
                            val biddingPrice:Long,
                            @get:JsonProperty("bidding_at")
                            val biddingAt:Instant
)
