package com.study.peoplefund.domain

import com.study.peoplefund.web.arguments.user.AuthInfo
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Session(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(unique = true)
    var token: String,

    @ManyToOne
    var user: User,

    var expiration: LocalDateTime
) {

    fun toAuthInfo(): AuthInfo {
        return AuthInfo(
            sessionId = id!!,
            userId = user.id!!
        )
    }
}