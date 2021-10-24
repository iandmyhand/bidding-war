package com.example.bidding_war.repository

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.model.Bidding
import org.springframework.data.jpa.repository.JpaRepository

interface BiddingRepository : JpaRepository<Bidding, Long?> {
    fun findByAuctionItemId(auctionItemId: Long): List<Bidding>
    fun existsByAmountGreaterThanEqualAndAuctionItemId(amount: Long, auctionItem_id: Long): Boolean
    fun findFirstByAuctionItemOrderByAmountDesc(auctionItem: AuctionItem): Bidding
}