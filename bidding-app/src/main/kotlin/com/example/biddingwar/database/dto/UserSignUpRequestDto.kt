package com.example.biddingwar.database.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UserSignUpRequestDto (
    @get: JsonProperty("email")
    var email: String,
    @get: JsonProperty("password")
    var password: String
)