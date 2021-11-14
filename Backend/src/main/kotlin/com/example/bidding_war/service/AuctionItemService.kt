package com.example.bidding_war.service

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.model.Bidding
import com.example.bidding_war.repository.AuctionItemRepository
import com.example.bidding_war.repository.BiddingRepository
import com.example.bidding_war.repository.UserRepository
import com.example.bidding_war.web.dto.AuctionItem.AuctionItemRequest
import com.example.bidding_war.web.dto.AuctionItem.AuctionItemResponse
import com.example.bidding_war.web.dto.AuctionItem.BidRequest
import com.example.bidding_war.web.dto.AuctionItem.SellRequest
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import javax.persistence.EntityNotFoundException

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
            isComplete = false,
            finalBiddingId = null
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
            biddings = auctionItem.biddings,
            isComplete = auctionItem.isComplete,
            finalBiddingId = auctionItem.finalBiddingId
        )
    }

    fun update(actionItem: AuctionItem) = auctionItemRepository.save(actionItem)

    fun delete(id: Long) = auctionItemRepository.deleteById(id)

    @Transactional
    fun bid(request: BidRequest): Long {
        val auctionItem = auctionItemRepository.findById(request.auctionItemId).orElseThrow()!!
        val owner = auctionItem.owner

        if (auctionItem.isComplete){
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not Acceptable")
        }
        if (biddingRepository.existsByAmountGreaterThanEqualAndAuctionItemId(request.amount, request.auctionItemId)) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Conflict")
        }

        if ((auctionItem.startPrice - request.amount) % auctionItem.minBiddingPrice != 0L){
            throw ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed")
        }

        if (owner != null) {
            if (owner.id == request.userId){
                throw ResponseStatusException(HttpStatus.NOT_FOUND, "NOT_FOUND")
            }
        }

        val bidding = biddingRepository.save(
            Bidding(
                auctionItem = auctionItem,
                amount = request.amount,
                user = userRepository.findById(request.userId).orElseThrow()
            )
        )

        return bidding.id!!
    }

    @Transactional
    fun sell(request: SellRequest): AuctionItemResponse {
        val auctionItem: AuctionItem = auctionItemRepository.findById(request.auctionItemId).orElseThrow()!!
        val owner = auctionItem.owner

        if (owner != null) {
            if (owner.id != request.userId){
                throw ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed")
            }
        }
        if (auctionItem.isComplete){
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Not Acceptable")
        }

        try {
            val bid: Bidding = biddingRepository.findFirstByAuctionItemOrderByAmountDesc(auctionItem)
            auctionItem.let {
                it.finalBiddingId = bid.id
                it.isComplete = true
                it.endTime = Instant.now()
            }
        } catch (e: EmptyResultDataAccessException){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "NOT_FOUND")
        }

        return AuctionItemResponse(
            id = auctionItem.id!!,
            title = auctionItem.title,
            email = owner!!.email,
            description = auctionItem.description,
            startPrice = auctionItem.startPrice,
            minBiddingPrice = auctionItem.minBiddingPrice,
            createDate = auctionItem.createDate,
            biddings = auctionItem.biddings,
            isComplete = auctionItem.isComplete,
            finalBiddingId = auctionItem.finalBiddingId,
            endTime = auctionItem.endTime
        )
    }

    @Scheduled(cron = "0 * * * * *")
    fun sellByTime(){
        val finishedAuctionItems: List<AuctionItem> = auctionItemRepository.findByIsisCompleteFalseAndendTimeLessThanEqual(
                Instant.now()
        )

        for (auctionItem in finishedAuctionItems) {
            val bid: Bidding = biddingRepository.findFirstByAuctionItemOrderByAmountDesc(auctionItem)
            auctionItem.let {
                it.finalBiddingId = bid.id
                it.isComplete = true
                it.endTime = Instant.now()
            }
        }


    }

}
