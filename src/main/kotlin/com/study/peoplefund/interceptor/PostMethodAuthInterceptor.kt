package com.study.peoplefund.interceptor

import com.study.peoplefund.service.AuthService
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class PostMethodAuthInterceptor(
        val authService: AuthService
) : HandlerInterceptor {
    override fun preHandle(
            request: HttpServletRequest,
            response: HttpServletResponse,
            handler: Any
    ): Boolean {
        if (request.method != "POST") {
            return true
        }

        val token = request.getHeader("Authorization")

        authService.validateToken(token)

        return true
    }
}