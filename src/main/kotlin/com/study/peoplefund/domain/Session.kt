package com.study.peoplefund.domain

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Session(
    @Column(unique = true)
    var token: String,
    var account: String,
    var expiration: LocalDateTime
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}
