package com.study.biddingwar.repository

import com.study.biddingwar.domain.entity.BiddingInfo
import org.springframework.data.jpa.repository.JpaRepository

interface BiddingInfoRepository:JpaRepository<BiddingInfo, Long> {

}