package com.example.bidding_war.service

import com.example.bidding_war.model.Session
import com.example.bidding_war.model.User
import com.example.bidding_war.repository.SessionRepository
import com.example.bidding_war.repository.UserRepository
import com.example.bidding_war.utils.PasswordUtils
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
    fun findById(id: Long) = userRepository.findById(id)

    @Transactional
    fun signUp(request: UserRequest): Long {
        val user = User(email = request.email, password = request.password)
        user.password = String(PasswordUtils.hash(user.password, PasswordUtils.generateSalt()), Charsets.UTF_16)
        val isExist = userRepository.existsByEmailAndPassword(request.email, user.password)
        return if (isExist){
            return 0L
        } else{
            userRepository.save(user).id!!
        }
    }

    @Transactional
    fun signIn(request: SignInRequest): SignInResponse {
        val hashedPassword = String(PasswordUtils.hash(request.password, PasswordUtils.generateSalt()), Charsets.UTF_16)

        val user = userRepository.findByEmailAndPassword(
            email = request.email,
            password = hashedPassword
        ).orElseThrow()

        val session = Session(
            token = UUID.randomUUID().toString(),
            userId = user.id!!,
            expiration = LocalDateTime.now().plusMinutes(30L)
        )

        sessionRepository.save(session)

        return SignInResponse(
            token = session.token,
            userId = session.userId
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