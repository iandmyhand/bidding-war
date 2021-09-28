package com.study.biddingwar.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.biddingwar.domain.entity.User
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthSuccessHandler(private val objectMapper: ObjectMapper):AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val detail:Map<String, Any> = authentication.details as Map<String, Any>
        val user:User = detail.get("user") as User

        val resultMap = mapOf(
            "user_id" to user.id,
            "latest_login_date" to user.updatedAt
        )

        val body = objectMapper.writeValueAsString(resultMap)
        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.writer.write(body)
    }
}