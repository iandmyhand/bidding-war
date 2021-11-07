package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class BiddingDto(@JsonIgnore
                      var productId:Long,
                      @get:JsonProperty("bidding_price")
                      val biddingPrice:Long,
                      @JsonIgnore
                      var userId:Long? = null
)
