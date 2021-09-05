package com.example.bidding_war.web.dto.AuctionItem

import com.example.bidding_war.model.AuctionItem

data class AuctionItemRequest(
    val title: String,
    val owner: Long,
    val description: String,
    val startPrice: Long,
    val minBiddingPrice: Long
)