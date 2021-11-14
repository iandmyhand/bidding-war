package com.example.bidding_war.repository

import com.example.bidding_war.model.AuctionItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.Instant


@Repository
interface AuctionItemRepository : JpaRepository<AuctionItem, Long> {
    fun findByIsisCompleteFalseAndendTimeLessThanEqual(endTime: Instant): List<AuctionItem>
}