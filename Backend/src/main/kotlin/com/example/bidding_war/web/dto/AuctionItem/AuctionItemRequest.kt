package com.example.bidding_war.web.dto.AuctionItem

import com.example.bidding_war.model.AuctionItem

data class AuctionItemRequest(
    val title: String,
    val owner: String,
    val description: String,
    val startPrice: Long,
    val biddingPrice: Long
) {
    fun toAuctionItem(): AuctionItem {
        return AuctionItem(
            title = this.title,
            owner = this.owner,
            description = this.description,
            startPrice = this.startPrice,
            biddingPrice = this.biddingPrice
        )
    }
}