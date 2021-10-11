package com.study.peoplefund.web.arguments.user

data class AuthInfo(
    val sessionId: Long,
    val userId: Long
)