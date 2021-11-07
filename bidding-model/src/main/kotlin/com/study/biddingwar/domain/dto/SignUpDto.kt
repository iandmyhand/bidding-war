package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SignUpDto(@get:JsonProperty("user_name")
                     val userName:String,
                     @get:JsonProperty("user_password")
                     val userPassword:String)
