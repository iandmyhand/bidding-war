package com.example.bidding_war.web.dto.User

import org.springframework.http.HttpStatus

data class UserResponse(
    val id: Long,
    val status: HttpStatus,
    val message: String,
)