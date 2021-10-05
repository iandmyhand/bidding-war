package com.study.biddingwar.service

import com.study.biddingwar.common.crypto.PasswordBCrypto
import com.study.biddingwar.domain.entity.User
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Service

@Service
class SignInService(private val userService: UserService,
                    private val sessionService: SessionService
) {

    fun signinUser(authId: String, authPw: String): User? {
        val user = userService.loadUserByUsername(authId)

        if (!PasswordBCrypto.checkPassword(authPw, user.password)) {
            // @TODO -> 기본제공되는 Exception 없어보임 // 커스텀 Exception (패스워드 불일치) 생성
            throw Exception("Not matched password")
        }

        return user
    }

    fun signoutUser(userId: String) {
        sessionService.removeSession(userId)
    }
}