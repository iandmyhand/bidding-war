package com.example.biddingwar

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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
    var user_name: String,
    val password: String
)