package kr.co.peoplefund.biddingWar.service

import kr.co.peoplefund.biddingWar.controller.dto.LoginRequest
import kr.co.peoplefund.biddingWar.controller.dto.UserRequest
import kr.co.peoplefund.biddingWar.controller.dto.LoginResponse
import kr.co.peoplefund.biddingWar.domain.Session
import kr.co.peoplefund.biddingWar.domain.User
import kr.co.peoplefund.biddingWar.domain.repository.SessionRepository
import kr.co.peoplefund.biddingWar.domain.repository.UserRepository
import kr.co.peoplefund.biddingWar.utils.PasswordUtils
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

    fun getUser(userId: Long): User {
        return userRepository.findById(userId).orElseThrow()
    }

    @Transactional(readOnly = true)
    fun validateToken(token: String): Session {
        val session = sessionRepository.findByKey(token).orElseThrow()
        println(session.userId)
        return session
    }
}