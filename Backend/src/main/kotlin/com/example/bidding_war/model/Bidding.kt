package com.example.bidding_war.model

import java.util.*
import javax.persistence.*

@Entity
class Bidding (
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auctionitem_id")
    var auctionItem: AuctionItem?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User?,

    var price: Long,

    var createDate: Date = Date()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @PrePersist
    private fun setCrDate() { createDate = Date() }
}