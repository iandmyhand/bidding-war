package com.example.biddingwar

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue val id: Long,
    var name: String,
    var price: Int = 0,
    val description: String
)

@Entity
data class User(
    @Id @GeneratedValue val id: Long,
    var email: String,
    var password: String,

    @CreationTimestamp
    var createDt: LocalDateTime = LocalDateTime.now()
)