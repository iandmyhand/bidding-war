package com.example.biddingwar.database.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class UserSignUpResponseDto (
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("email")
    val email: String,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime
)