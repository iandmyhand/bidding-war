package com.study.biddingwar

import javax.persistence.* // Spring Data JPA(Java Persistance API)
import java.time.LocalDateTime

@Entity
data class Item(
    @Id @GeneratedValue val id: Long,
    var seller: String,
    var title: String,
    var category: String,
    var initialPrice: Long = 1000,
    var description: String? = "",
    var status: String = "registered",
    var registered: LocalDateTime = LocalDateTime.now(),
    var finished: LocalDateTime? = null,
)
