package com.example.bidding_war.model

import javax.persistence.*


@Entity
class Session (
    @Column(unique = true)
    var key: String,
    var email: String?
){
    @Id
    @GeneratedValue
    var id: Long? = null
}