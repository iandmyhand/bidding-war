package com.example.bidding_war.interceptor

import com.example.bidding_war.service.UserService
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class PostMethodAuthInterceptor(
    val userService: UserService
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

        userService.validateToken(token)

        return true
    }
}