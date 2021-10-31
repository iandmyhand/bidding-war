package com.study.pf.bidding.common.exception

import com.study.pf.common.common.exception.BiddingError
import org.springframework.http.HttpStatus

abstract class BaseException(
        val error: BiddingError,
        val httpStatus: HttpStatus,
        val data: Map<String, Any>? = null,
        message: String,
        val exception: Exception? = null
): RuntimeException(message, exception)