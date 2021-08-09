package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.User

class UserRequest(
    val userId: String,
    val password: String
) {
    fun toUser(): User {
        return User(
            userId = this.userId,
            password = this.password
        )
    }
}