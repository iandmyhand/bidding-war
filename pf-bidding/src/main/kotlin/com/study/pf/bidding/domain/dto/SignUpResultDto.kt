package com.study.pf.bidding.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

/**
 * 회원가입 Result 간략히 구성
 * 아이디(email x), 이름, 닉네임, 생성일
 */
data class SignUpResultDto(
        @get:JsonProperty("user_id")
        val userId: String,

        @get:JsonProperty("user_nick")
        val userNick: String,

        @get:JsonProperty("user_name")
        val userName: String,

        @get:JsonProperty("create_dt")
        val createDt: Instant
)