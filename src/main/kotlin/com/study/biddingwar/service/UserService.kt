package com.study.biddingwar.service

import com.study.biddingwar.controller.dto.LoginRequest
import com.study.biddingwar.controller.dto.UserRequest
import com.study.biddingwar.controller.dto.LoginResponse
import com.study.biddingwar.domain.Session
import com.study.biddingwar.domain.repository.SessionRepository
import com.study.biddingwar.domain.repository.UserRepository
import com.study.biddingwar.utils.PasswordUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(val userRepository: UserRepository, val sessionRepository: SessionRepository) {

    fun register(request: UserRequest): Long {
        val user = request.toUser()
        user.password = String(PasswordUtils.hash(user.password, PasswordUtils.generateSalt()), Charsets.UTF_16)
        return userRepository.save(user).id!!
    }

    fun login(request: LoginRequest): LoginResponse {
        val hashedPassword = String(PasswordUtils.hash(request.password, PasswordUtils.generateSalt()), Charsets.UTF_16)
        val user = userRepository.findByEmailAndPassword(
            email = request.email,
            password = hashedPassword
        ).orElseThrow()

        val session = Session(
            key = UUID.randomUUID().toString(),
            userId = user.id
        )
        sessionRepository.save(session)

        return LoginResponse.of(user, session)
    }

    @Transactional(readOnly = true)
    fun validateToken(token: String) {
        val session = sessionRepository.findByKey(token).orElseThrow()
        println(session.userId)
    }
}
