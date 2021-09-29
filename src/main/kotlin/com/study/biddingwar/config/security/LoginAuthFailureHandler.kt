package com.study.biddingwar.config.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginAuthFailureHandler: AuthenticationFailureHandler {
    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun onAuthenticationFailure(request: HttpServletRequest,
                                         response: HttpServletResponse,
                                         exception: AuthenticationException) {
        logger.info("login Failed")
        response!!.contentType = "text/html; charset=UTF-8"

        val out = response!!.writer
        // 한글로 일단..
        out.println("<script>alert(\"아이디가 존재하지 않거나\n 패스워드가 일치하지 않습니다.\");</script>")

        out.flush()
    }
}