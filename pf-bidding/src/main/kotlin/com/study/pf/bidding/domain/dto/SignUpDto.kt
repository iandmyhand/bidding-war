package com.study.pf.bidding.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * 회원가입 간략히 구성
 * 아이디(email x), 이름, 닉네임, 패스워드
 */
data class SignUpDto(
        @get:JsonProperty("user_id")
        val userId: String,

        @get:JsonProperty("user_nick")
        val userNick: String,

        @get:JsonProperty("user_name")
        val userName: String,

        @get:JsonProperty("user_password")
        val userPassword: String
)