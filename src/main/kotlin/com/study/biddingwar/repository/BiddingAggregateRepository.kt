package com.study.biddingwar.repository

import com.study.biddingwar.domain.aggregate.BiddingAggregate
import org.springframework.data.jpa.repository.JpaRepository

interface BiddingAggregateRepository:JpaRepository<BiddingAggregate, Long> {
    fun findAllByProductInfoId(productId:Long):List<BiddingAggregate>
    fun findByProductInfoIdAndUserId(productId: Long, userId:Long):BiddingAggregate?
}