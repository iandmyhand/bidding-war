package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class SignUpResultDto(
    @get:JsonProperty("user_id")
    val userId:Long,
    @get:JsonProperty("user_name")
    val userName:String,
    @get:JsonProperty("created_at")
    val createdAt: Instant
)
