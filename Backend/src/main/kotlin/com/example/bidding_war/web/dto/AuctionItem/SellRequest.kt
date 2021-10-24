package com.example.bidding_war.web.dto.AuctionItem

data class SellRequest (
    val auctionItemId: Long,
    val userId: Long,
)