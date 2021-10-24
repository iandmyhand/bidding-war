package com.example.bidding_war.web.dto.AuctionItem

import com.example.bidding_war.model.Bidding
import java.util.*


data class AuctionItemResponse(
    val id: Long,
    val title: String,
    val email: String,
    val description: String,
    val startPrice: Long,
    val minBiddingPrice: Long,
    val createDate: Date,
    val biddings: MutableList<Bidding>?,
    val isComplete: Boolean,
    val finalBiddingId: Long?
)