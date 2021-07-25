package com.study.biddingwar

import javax.persistence.* // Spring Data JPA(Java Persistance API)

@Entity
data class Bid(
    @Id @GeneratedValue val id: Long,
    val itemId: Long,
    val buyer: String,
    val price: Long,
)