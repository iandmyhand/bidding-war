package com.study.peoplefund.service

import com.study.peoplefund.domain.Session
import com.study.peoplefund.domain.User
import com.study.peoplefund.domain.repository.SessionRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.security.PasswordHasher
import com.study.peoplefund.web.arguments.user.AuthInfo
import com.study.peoplefund.web.dto.SignInRequest
import com.study.peoplefund.web.dto.SignInResponse
import com.study.peoplefund.web.dto.UserRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class AuthService(
        val userRepository: UserRepository,
        val sessionRepository: SessionRepository,
        val passwordHasher: PasswordHasher
) {

    @Transactional
    fun signUp(request: UserRequest): Long {
        val salt = passwordHasher.generateSalt()

        if (request.password.length < 4) {
            throw IllegalArgumentException("비밀번호는 4자 이상이어야 합니다.")
        }

        val hashedPassword = passwordHasher.hash(request.password, salt)
        val user = User(
            account = request.account,
            password = hashedPassword,
            name = request.name,
            salt = salt
        )

        return userRepository.save(user).id!!
    }

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val user = userRepository.findByAccount(account = request.account).orElseThrow()

        val requestHashedPassword = passwordHasher.hash(request.password, user.salt)

        if (requestHashedPassword != user.password) {
            throw SecurityException()
        }

        val session = Session(
                token = UUID.randomUUID().toString(),
                user = user,
                expiration = LocalDateTime.now().plusMinutes(30L)
        )

        sessionRepository.save(session)

        return SignInResponse(
                token = session.token,
                account = user.account
        )
    }

    @Transactional(readOnly = true)
    fun validateToken(token: String): AuthInfo {
        val session = sessionRepository.findByToken(token).orElseThrow()

        if (session.expiration.isBefore(LocalDateTime.now())) {
            throw SecurityException()
        }

        return session.toAuthInfo()
    }
}