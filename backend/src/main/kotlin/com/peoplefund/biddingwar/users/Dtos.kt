package com.peoplefund.biddingwar.users

import javax.validation.constraints.Size


data class UserSignupRequest(
    @Size(min=5, max=30) val userId: String,
    @Size(min=5, max=20) val password: String
)

data class UserSigninResponse(
    @Size(min=5, max=30) val userId: String,
    val loginSuccess: Boolean,
) {
    constructor(user: User) : this(user.userId, true)
}