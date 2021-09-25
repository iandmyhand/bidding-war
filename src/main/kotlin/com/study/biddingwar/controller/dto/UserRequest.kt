package com.study.biddingwar.controller.dto

import com.study.biddingwar.domain.User

class UserRequest(
    val email: String,
    val password: String
) {
    fun toUser(): User {
        return User(
            email = this.email,
            password = this.password,
        )
    }
}
