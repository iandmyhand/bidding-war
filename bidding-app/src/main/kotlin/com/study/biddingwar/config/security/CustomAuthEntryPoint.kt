package com.study.biddingwar.config.security

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CustomAuthEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        if (authException != null) {
            logger.debug(authException.message)
            response!!.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized")
        }
    }

    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }
}