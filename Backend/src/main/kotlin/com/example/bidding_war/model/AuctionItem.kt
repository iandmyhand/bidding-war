package com.example.bidding_war.model


import java.util.*
import javax.persistence.*


@Entity
class AuctionItem (
    var title: String,
    var owner: String,
    var description: String,
    var startPrice: Long,
    var biddingPrice: Long,
    var createDate: Date = Date()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @PrePersist
    private fun setCrDate() { createDate = Date() }
}