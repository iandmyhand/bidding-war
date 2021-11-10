package com.study.pf.bidding.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

/**
 *
 */
data class SignInDto (
        @get:JsonProperty("user_id")
        val userId: String,

        @get:JsonProperty("user_password")
        val userPassword: String
)