package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.User

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