package com.study.biddingwar.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CsrfTokenException::class)
    fun csrfTokenExceptionHanle(e:CsrfTokenException){
        logger.error("${ErrorType.CSRF_TOKEN.message}, ${e.csrfToken}")
        ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse.wrap<Any>(ErrorType.CSRF_TOKEN))
    }

    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}