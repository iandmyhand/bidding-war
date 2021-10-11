package com.example.biddingwar.database

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Product(
    @Id @GeneratedValue val id: Long,
    var userId: Long? = null,
    var name: String,
    var price: Int = 0,
    val description: String,
    var minimumPrice: Int = 0,
    var finalBidPrice: Int = 0,
    var isBidComplete: Boolean = false
)

