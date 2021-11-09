package com.study.peoplefund.web.dto

import java.time.LocalDateTime

data class ProductRequest(
    val name: String,
    val price: Long,
    val endDateTime: LocalDateTime? = null
)