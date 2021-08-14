package com.study.biddingwar

import javax.persistence.* // Spring Data JPA(Java Persistance API)
import java.time.LocalDateTime

@Entity
class Item(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var seller: String,

    var title: String,

    var category: String,

    var initialPrice: Long = 1000,

    var description: String? = "",

    var status: String = "registered",

    var registered: LocalDateTime = LocalDateTime.now(),

    var finished: LocalDateTime? = null,
)
