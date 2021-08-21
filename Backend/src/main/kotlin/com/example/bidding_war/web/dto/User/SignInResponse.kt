package com.example.bidding_war.web.dto.User

data class SignInResponse(
    val token: String,
    val email: String
)