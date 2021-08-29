package com.example.biddingwar.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Bid (
    @Id
    @GeneratedValue
    val id: Long? = null,
    @Column(name = "ItemId")
    var itemId: Long,
    @Column(name = "UserId")
    var userId: Long,
    @Column(name = "price")
    var price: Int,
    @CreatedDate
    val createdTime: LocalDateTime = LocalDateTime.now()
    ){}