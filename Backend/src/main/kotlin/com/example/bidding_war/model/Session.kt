package com.example.bidding_war.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Session(
    @Column(unique = true)
    var token: String,
    var email: String,
    var expiration: LocalDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}