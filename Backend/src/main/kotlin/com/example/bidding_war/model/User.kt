package com.example.bidding_war.model


import java.util.*
import javax.persistence.*


@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var email: String? = null,
    var password: String? = null,
    var createDate: Date = Date()
) {

    @PrePersist
    private fun setCrDate() { createDate = Date() }
}