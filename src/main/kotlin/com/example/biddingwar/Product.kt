package com.example.biddingwar

import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Product(
    @Id
    @GeneratedValue
    val id: Long,

    val name: String,

    val description: String,

    var price: Int,

    var minimumPrice: Int,

    // @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now(),
)