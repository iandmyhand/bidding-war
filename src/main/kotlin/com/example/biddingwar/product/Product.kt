package com.example.biddingwar.product

import com.example.biddingwar.account.Account
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

    @ManyToOne
    var seller: Account?,

    // @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now(),

    val biddingEndDateTime: LocalDateTime?
)