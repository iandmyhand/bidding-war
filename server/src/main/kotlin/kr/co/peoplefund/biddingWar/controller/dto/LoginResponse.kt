package kr.co.peoplefund.biddingWar.controller.dto

import kr.co.peoplefund.biddingWar.domain.Session
import kr.co.peoplefund.biddingWar.domain.User

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