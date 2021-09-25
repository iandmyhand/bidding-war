package com.example.biddingwar.database

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Bid(
    @Id @GeneratedValue
    val id: Long,
    var productId: Long,
    var userId: Long,
    var biddingPrice: Int = 0,
)