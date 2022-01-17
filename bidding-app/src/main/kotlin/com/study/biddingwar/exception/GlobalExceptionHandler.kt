package com.study.biddingwar.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CsrfTokenException::class)
    fun csrfTokenExceptionHandle(e:CsrfTokenException): ResponseEntity<ErrorResponse<Any>> {
        logger.error("${ErrorType.CSRF_TOKEN.message}, ${e.csrfToken}")
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse.wrap<Any>(ErrorType.CSRF_TOKEN))
    }

    @ExceptionHandler(DataDuplicationException::class)
    fun dataDuplicationExceptionHandle(e:DataDuplicationException): ResponseEntity<ErrorResponse<Any>> {
        logger.debug(e.message)
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse.wrap<Any>(ErrorType.DATA_DUPLICATION))
    }

    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}