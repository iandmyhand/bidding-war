package com.example.bidding_war.service

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.repository.AuctionItemRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class AuctionItemService(val auctionItemRepository: AuctionItemRepository) {

    fun register(actionItem: AuctionItem) = auctionItemRepository.save(actionItem)

    fun findAll() = auctionItemRepository.findAll()

    fun findById(id: Long) = auctionItemRepository.findById(id)

    fun update(actionItem: AuctionItem) = auctionItemRepository.save(actionItem)

    fun delete(id: Long) = auctionItemRepository.deleteById(id)

}