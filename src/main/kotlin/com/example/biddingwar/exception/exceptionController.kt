package com.example.biddingwar.exception

import javassist.NotFoundException
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException

@ControllerAdvice
class GlobalExceptionController{
    @ExceptionHandler(value = [ResponseStatusException::class])
    fun responseStatusException(e: ResponseStatusException, model: Model): String {

        model["status"] = e.status
        model["message"] = e.reason.toString()
        return "exceptions/responseStatus"
    }

    @ExceptionHandler(value = [RuntimeException::class])
    fun runtimeException(e: RuntimeException, model: Model): String {

        model["message"] = "500Error 개발자에게 문의해주세요 ${e.message}"
        return "exceptions/exception"
    }
}