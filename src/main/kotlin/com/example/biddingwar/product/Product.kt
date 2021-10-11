package com.example.biddingwar.product

import com.example.biddingwar.account.Account
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Min


@Entity
data class Product(
    @Id
    @GeneratedValue
    val id: Long? = null,

    val name: String,

    val description: String,

    @field:Min(value = 1)
    var price: Int,

    var minimumPrice: Int,

    @ManyToOne
    var seller: Account?,

    // @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now(),
)