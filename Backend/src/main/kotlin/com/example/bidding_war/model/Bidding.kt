package com.example.bidding_war.model

import java.util.*
import javax.persistence.*

@Entity
class Bidding (
    @ManyToOne
    var auctionItem: AuctionItem,

    @ManyToOne
    var user: User,

    var amount: Long,

    var createDate: Date = Date()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @PrePersist
    private fun setCrDate() { createDate = Date() }
}