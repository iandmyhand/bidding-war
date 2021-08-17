package com.study.peoplefund.service

import com.study.peoplefund.domain.Session
import com.study.peoplefund.domain.repository.SessionRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.web.dto.SignInRequest
import com.study.peoplefund.web.dto.SignInResponse
import com.study.peoplefund.web.dto.UserRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class UserService(
        val userRepository: UserRepository,
        val sessionRepository: SessionRepository
) {

    @Transactional
    fun signUp(request: UserRequest): Long {
        val user = request.toUser()
        return userRepository.save(user).id!!
    }

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val user = userRepository.findByAccountAndPassword(
                account = request.account,
                password = request.password
        ).orElseThrow()

        val session = Session(
                token = UUID.randomUUID().toString(),
                account = user.account,
                expiration = LocalDateTime.now().plusMinutes(30L)
        )

        sessionRepository.save(session)

        return SignInResponse(
                token = session.token,
                account = session.account
        )
    }

    @Transactional(readOnly = true)
    fun validateToken(token: String) {
        val session = sessionRepository.findByToken(token).orElseThrow()

        if (session.expiration.isBefore(LocalDateTime.now())) {
            throw SecurityException()
        }
    }
}