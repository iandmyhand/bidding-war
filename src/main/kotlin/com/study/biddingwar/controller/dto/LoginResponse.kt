package com.study.biddingwar.controller.dto

import com.study.biddingwar.domain.Session
import com.study.biddingwar.domain.User

class LoginResponse (
    val id: Long,
    val sessionKey: String
) {
    companion object {
        fun of(user: User, session: Session): LoginResponse {
            return LoginResponse(
                id = user.id!!,
                sessionKey = session.key
            )
        }
    }
}
