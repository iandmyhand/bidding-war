package com.example.bidding_war.model


import java.util.*
import javax.persistence.*


@Entity
data class User (
    @Column(unique = true)
    var email: String,
    var password: String,

    @OneToMany
    val auctionItems: MutableList<AuctionItem>? = null,

    @OneToMany
    val biddingLists: MutableList<Bidding>? = null,

    var createDate: Date = Date()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?= null

    @PrePersist
    private fun setCrDate() { createDate = Date() }
}