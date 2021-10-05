package com.study.biddingwar.config

import com.study.biddingwar.common.exception.BaseException
import com.study.biddingwar.common.exception.BiddingError
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionConf {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(this::class.java)
    }

    private fun response(httpStatus: HttpStatus,
                         error: BiddingError,
                         data: Map<String, Any>?= null
    ): ResponseEntity<Map<String, Any?>> {
        val response: Map<String, Any?> = mapOf(
            "code" to error.code,
            "message" to error.message,
            "data" to data
        )

        return ResponseEntity.status(httpStatus).body(response)
    }

    @ExceptionHandler(BaseException::class)
    fun handleBaseException(exception: BaseException): ResponseEntity<Map<String, Any?>> {

        if (exception.httpStatus.is5xxServerError) {
            LOGGER.error(exception.message, exception.exception)
        } else {
            LOGGER.debug(exception.message)
        }

        return this.response(exception.httpStatus, exception.error, exception.data)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<Map<String, Any?>> {
        LOGGER.error(exception.message, exception)
        return this.response(HttpStatus.INTERNAL_SERVER_ERROR, BiddingError.Common.UNEXPECTED_ERROR)
    }
}