package com.example.bidding_war.repository

import com.example.bidding_war.model.Bidding
import org.springframework.data.jpa.repository.JpaRepository

interface BiddingRepository : JpaRepository<Bidding, Long?> {
}