package com.study.peoplefund.web.dto

data class SignInRequest(
        val account: String,
        val password: String
)