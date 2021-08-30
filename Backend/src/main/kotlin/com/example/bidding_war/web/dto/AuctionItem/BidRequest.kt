package com.example.bidding_war.web.dto.AuctionItem

data class BidRequest (
    val auctionItemId: Long,
    val userId: Long,
    val amount: Int
)