package com.study.biddingwar.config.security

import com.study.biddingwar.common.util.SessionUtil
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginAuthSuccessHandler: AuthenticationSuccessHandler {
    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    /**
     * @TODO : 현재 JSession 으로 구현 --> Redis 로 변경할 것!! (lettuce)
     */
    override fun onAuthenticationSuccess(request: HttpServletRequest,
                                         response: HttpServletResponse,
                                         authentication: Authentication) {
        SessionUtil.setData(SessionUtil.SESSION_DATA_KEY, "")
    }
}