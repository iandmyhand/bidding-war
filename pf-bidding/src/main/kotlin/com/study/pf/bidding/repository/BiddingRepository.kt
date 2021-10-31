package com.study.pf.bidding.repository

import com.study.pf.bidding.domain.entity.Bidding
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BiddingRepository: JpaRepository<Bidding, Long> {

}