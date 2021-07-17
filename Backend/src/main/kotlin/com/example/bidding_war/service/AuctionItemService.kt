package com.example.bidding_war.service

import com.example.bidding_war.model.AuctionItem
import com.example.bidding_war.repository.AuctionItemRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class AuctionItemService(val auctionItemRepository: AuctionItemRepository) {

    fun register(actionItem: AuctionItem) = Mono.fromCallable {
        auctionItemRepository.save(actionItem)
    }.subscribeOn(Schedulers.parallel())

    fun findAll() = Mono.fromCallable {
        auctionItemRepository.findAll()
    }.subscribeOn(Schedulers.parallel())
        .flatMapMany { Flux.fromIterable(it) }

    fun findById(id: Long) =
        Mono.fromCallable {
            auctionItemRepository.findById(id)
        }.subscribeOn(Schedulers.parallel())

    fun update(actionItem: AuctionItem) = Mono.fromCallable {
        auctionItemRepository.save(actionItem)
    }.subscribeOn(Schedulers.parallel())

    fun delete(id: Long) = Mono.fromCallable {
        auctionItemRepository.deleteById(id)
    }.subscribeOn(Schedulers.parallel())
}