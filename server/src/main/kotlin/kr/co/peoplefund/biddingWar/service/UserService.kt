package kr.co.peoplefund.biddingWar.service

import kr.co.peoplefund.biddingWar.controller.dto.LoginRequest
import kr.co.peoplefund.biddingWar.controller.dto.UserRequest
import kr.co.peoplefund.biddingWar.controller.dto.LoginResponse
import kr.co.peoplefund.biddingWar.domain.Session
import kr.co.peoplefund.biddingWar.domain.User
import kr.co.peoplefund.biddingWar.domain.repository.SessionRepository
import kr.co.peoplefund.biddingWar.domain.repository.UserRepository
import kr.co.peoplefund.biddingWar.utils.PasswordUtils
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class UserService(val userRepository: UserRepository, val sessionRepository: SessionRepository) {

    fun register(request: UserRequest): Long {
        val user = request.toUser()
        if (user.password.length < 4) {
            throw ResponseStatusException(
                HttpStatus.NOT_ACCEPTABLE,
                "Password length has to be greater than or equal to 4.")
        }
        if (userRepository.existsByEmail(user.email)) {
            throw ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Given email is using by other user already.")
        }
        user.password = String(PasswordUtils.hash(user.password, PasswordUtils.generateSalt()), Charsets.UTF_16)
        return userRepository.save(user).id!!
    }

    fun login(request: LoginRequest): LoginResponse {
        val hashedPassword = String(PasswordUtils.hash(request.password, PasswordUtils.generateSalt()), Charsets.UTF_16)
        val user = userRepository.findByEmailAndPassword(
            email = request.email,
            password = hashedPassword
        ).orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.") }

        val session = Session(
            key = UUID.randomUUID().toString(),
            userId = user.id
        )
        sessionRepository.save(session)
        return LoginResponse.of(user, session)
    }

    fun getUser(userId: Long): User {
        return userRepository
            .findById(userId)
            .orElseThrow{ ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.") }
    }

    @Transactional(readOnly = true)
    fun validateToken(token: String): Session {
        val session = sessionRepository
            .findByKey(token)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Not found.") }
        println(session.userId)
        return session
    }
}