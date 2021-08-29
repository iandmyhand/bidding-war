package com.study.peoplefund.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @Column(unique = true)
        var account: String,

        var password: String,

        var name: String,

        var salt: ByteArray
)