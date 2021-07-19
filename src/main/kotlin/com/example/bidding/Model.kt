package com.example.bidding

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
