package com.example.bidding_war.web.dto.AuctionItem

import com.example.bidding_war.model.AuctionItem

data class AuctionItemResponse(
    val id: Long,
    val title: String,
    val owner: String,
    val description: String,
    val startPrice: Long,
    val biddingPrice: Long
) {
    companion object {
        fun of(auctionItem: AuctionItem): AuctionItemResponse {
            return AuctionItemResponse(
                id = auctionItem.id!!,
                title = auctionItem.title,
                owner = auctionItem.owner,
                description = auctionItem.description,
                startPrice = auctionItem.startPrice,
                biddingPrice = auctionItem.minBiddingPrice
            )
        }

        fun of(auctionItems: List<AuctionItem>): List<AuctionItemResponse> {
            return auctionItems.map { of(it) }
        }
    }
}