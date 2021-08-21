package com.example.bidding_war.service

import com.example.bidding_war.model.Session
import com.example.bidding_war.repository.SessionRepository
import com.example.bidding_war.repository.UserRepository
import com.example.bidding_war.web.dto.User.SignInRequest
import com.example.bidding_war.web.dto.User.SignInResponse
import com.example.bidding_war.web.dto.User.UserRequest
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
        val user = userRepository.findByEmailAndPassword(
            email = request.email,
            password = request.password
        ).orElseThrow()

        val session = Session(
            token = UUID.randomUUID().toString(),
            email = user.email,
            expiration = LocalDateTime.now().plusMinutes(30L)
        )

        sessionRepository.save(session)

        return SignInResponse(
            token = session.token
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