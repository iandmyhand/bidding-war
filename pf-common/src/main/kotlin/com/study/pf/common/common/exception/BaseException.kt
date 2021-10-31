package com.study.pf.common.common.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
        val error: BiddingError,
        val httpStatus: HttpStatus,
        val data: Map<String, Any>? = null,
        message: String,
        val exception: Exception? = null
): RuntimeException(message, exception)