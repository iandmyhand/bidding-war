package com.example.biddingwar.database

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Min

@Entity
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Product(
    @Id @GeneratedValue val id: Long,
    var userId: Long? = null,
    var name: String,

    @Min(value = 0, message = "price must be positive integer")
    var price: Int = 0,
    val description: String,

    @Min(value = 0, message = "minimum price must be positive integer")
    var minimumPrice: Int = 0,

    @Min(value = 0, message = "final bid price must be positive integer")
    var finalBidPrice: Int = 0,
    var isBidComplete: Boolean = false,

    var biddingEndTime: Instant? = null // 경매 종료 시각
)

