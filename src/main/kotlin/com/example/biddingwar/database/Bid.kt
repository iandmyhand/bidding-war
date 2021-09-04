package com.example.biddingwar.database

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class Bid(
    @Id @GeneratedValue
    val id: Long,
    var productId: Long,
    var userId: Long,
    var biddingPrice: Int = 0,
)