package com.example.bidding_war.repository

import com.example.bidding_war.model.AuctionItem
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.awt.print.Pageable

@Repository
interface AuctionItemRepository : CrudRepository<AuctionItem, Long> {
}