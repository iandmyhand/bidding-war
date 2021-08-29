package com.example.bidding_war.service

import com.example.bidding_war.model.Bidding
import com.example.bidding_war.repository.AuctionItemRepository
import com.example.bidding_war.repository.BiddingRepository
import com.example.bidding_war.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class BiddingService(
    val biddingRepository: BiddingRepository,
    val userRepository: UserRepository,
    val auctionItemRepository: AuctionItemRepository
) {

    fun register(bidding: Bidding) = biddingRepository.save(bidding)

    fun findAll() = biddingRepository.findAll()

    fun findById(id: Long) = biddingRepository.findById(id)

    fun update(bidding: Bidding) = biddingRepository.save(bidding)

    fun delete(id: Long) = biddingRepository.deleteById(id)

    fun bidOneAuctionItem(bidding: Bidding) {
        auctionItem = auctionItemRepository.findById(bidding.auctionItem)
    }

}