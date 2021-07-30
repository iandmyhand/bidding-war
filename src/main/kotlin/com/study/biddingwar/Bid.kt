package com.study.biddingwar

import javax.persistence.* // Spring Data JPA(Java Persistance API)

@Entity
data class Bid(
    @Id @GeneratedValue val id: Long,
    @ManyToOne var item: Item,
    var buyer: String,
    var price: Long,
)