package com.study.biddingwar

import javax.persistence.* // Spring Data JPA(Java Persistance API)

@Entity
class Bid(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @ManyToOne
    var item: Item,

    var buyer: String,

    var price: Long,
)