package com.example.bidding_war.service

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.model.Bidding
import com.example.bidding_war.repository.AuctionItemRepository
import com.example.bidding_war.repository.BiddingRepository
import com.example.bidding_war.repository.UserRepository
import com.example.bidding_war.web.dto.AuctionItem.AuctionItemRequest
import com.example.bidding_war.web.dto.AuctionItem.AuctionItemResponse
import com.example.bidding_war.web.dto.AuctionItem.BidRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.acos

@Service
class AuctionItemService(
    val auctionItemRepository: AuctionItemRepository,
    val biddingRepository: BiddingRepository,
    val userRepository: UserRepository
) {

    @Transactional
    fun register(request: AuctionItemRequest): Long{
        val user = userRepository.findById(request.owner).orElseThrow()!!
        val auctionItem = auctionItemRepository.save(AuctionItem(
            title = request.title,
            owner = user,
            biddings = null,
            description = request.description,
            startPrice = request.startPrice,
            minBiddingPrice = request.minBiddingPrice,
            )
        )
        return auctionItem.id!!

    }

    fun findAll() = auctionItemRepository.findAll()

    fun findById(id: Long): AuctionItemResponse{
        val auctionItem = auctionItemRepository.findById(id).orElseThrow()!!
        val user = auctionItem.owner
        return AuctionItemResponse(
            id = auctionItem.id!!,
            title = auctionItem.title,
            email = user!!.email,
            description = auctionItem.description,
            startPrice = auctionItem.startPrice,
            minBiddingPrice = auctionItem.minBiddingPrice,
            createDate = auctionItem.createDate,
            biddings = auctionItem.biddings
        )
    }

    fun update(actionItem: AuctionItem) = auctionItemRepository.save(actionItem)

    fun delete(id: Long) = auctionItemRepository.deleteById(id)

    @Transactional
    fun bid(request: BidRequest): Long {
        val auctionItem = auctionItemRepository.findById(request.auctionItemId).orElseThrow()!!

        if (biddingRepository.existsByAmountGreaterThanEqualAndAuctionItemId(request.amount, request.auctionItemId)) {
            throw IllegalArgumentException("요청 가격보다 큰 입찰 금액이 이미 존재합니다.")
        }

        if (auctionItem.minBiddingPrice > request.amount){
            throw IllegalArgumentException("요청 가격이 최소 주문 금액보다 작습니다.")
        }



        val bidding = biddingRepository.save(Bidding(
            auctionItem = auctionItem,
            amount = request.amount,
            user = userRepository.findById(request.userId).orElseThrow()
        )
        )

        return bidding.id!!
    }
}