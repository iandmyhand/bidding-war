package com.study.peoplefund.web.dto

data class UserRequest(
        val account: String,
        val password: String,
        val name: String
)
