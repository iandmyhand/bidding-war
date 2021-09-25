package com.study.biddingwar.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(var email: String, var password: String) {
    @Id
    @GeneratedValue
    var id: Long? = null
}
