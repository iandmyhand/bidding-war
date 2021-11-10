package com.study.pf.bidding.domain.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class SessionDto (
        @get:JsonProperty("session_id")
        val sessionId: String?= "Default",
        @get:JsonProperty("remote_ip_no")
        val remoteIpNo: Long,
        @get:JsonProperty("user_id")
        val userId: String,
        @get:JsonProperty("user_nick")
        val userNick: String,
        @get:JsonProperty("user_name")
        val userName: String
)


