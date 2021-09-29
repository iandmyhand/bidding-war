package com.study.biddingwar.config.security

import com.study.biddingwar.domain.dto.SessionDto
import com.study.biddingwar.service.SessionService
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class LoginAuthSuccessHandler(private val sessionService: SessionService): AuthenticationSuccessHandler {
    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    override fun onAuthenticationSuccess(request: HttpServletRequest,
                                         response: HttpServletResponse,
                                         auth: Authentication) {
        logger.debug("login id : ${auth.principal}")

        if (auth.details != null) {
            val sessionDto = auth.details as SessionDto

            if (!sessionDto.userId.isNullOrBlank()) {
                try {
                    sessionService.setSessionDto(sessionDto)
                    response.writer.write("login ok")
                    response.status = 200
                } catch (exception: Exception) {
                    throw Exception("tlqk")
                }
            }
        }
    }
}