package com.example.bidding_war.model


import java.util.*
import javax.persistence.*


@Entity
data class AuctionItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var title: String? = null,
    var owner: String? = null,
    var description: String? = null,
    var startPrice: Long? = null,
    var biddingPrice: Long? = null,
    var createDate: Date = Date()
) {
    @PrePersist
    private fun setCrDate() { createDate = Date() }
}