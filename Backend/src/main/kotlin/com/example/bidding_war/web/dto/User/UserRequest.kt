package com.example.bidding_war.web.dto.User

import com.example.bidding_war.model.User

data class UserRequest(
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