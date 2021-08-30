package com.example.bidding_war.service

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.model.Bidding
import com.example.bidding_war.repository.AuctionItemRepository
import com.example.bidding_war.repository.BiddingRepository
import com.example.bidding_war.repository.UserRepository
import com.example.bidding_war.web.dto.AuctionItem.AuctionItemRequest
import com.example.bidding_war.web.dto.AuctionItem.BidRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BiddingService(
    val biddingRepository: BiddingRepository,
    val userRepository: UserRepository,
    val auctionItemRepository: AuctionItemRepository
) {

    @Transactional
    fun register(request: BidRequest): Long{
        val user = userRepository.findById(request.userId).orElseThrow()!!
        val auctionItem = auctionItemRepository.findById(request.auctionItemId).orElseThrow()!!

        val bidding = biddingRepository.save(
            Bidding(
                auctionItem = auctionItem,
                user = user,
                amount = request.amount
            )
        )
        return bidding.id!!

    }

    fun findAll() = biddingRepository.findAll()

    fun findById(id: Long) = biddingRepository.findById(id)

    fun findByAuctionId(id:Long) = biddingRepository.findByAuctionItemId(id)

    fun update(bidding: Bidding) = biddingRepository.save(bidding)

    fun delete(id: Long) = biddingRepository.deleteById(id)

}