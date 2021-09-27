package com.study.biddingwar.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionDto (
        @get:JsonProperty("session_id")
        val sessionId: String?= "Default",

        @get:JsonProperty("remote_ip_no")
        val remoteIpNo: Long,

//      val accessToken: String?, // 일단 미구현

        @get:JsonProperty("user_id")
        val userId: String,

        @get:JsonProperty("user_nick")
        val userNick: String,

        @get:JsonProperty("user_name")
        val userName: String
)


