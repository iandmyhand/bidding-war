package com.example.bidding_war.model

import java.time.Instant
import java.util.*
import javax.persistence.*


@Entity
class AuctionItem(
    val title: String,

    @ManyToOne
    val owner: User?,

    @OneToMany
    var biddings: MutableList<Bidding>?,

    val description: String,
    val startPrice: Long,
    val minBiddingPrice: Long,
    var createDate: Date = Date(),
    var isComplete: Boolean = false,
    var finalBiddingId: Long?,
    var endTime: Instant? = null
    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @PrePersist
    private fun setCrDate() { createDate = Date() }
}