package com.example.biddingwar.service.user

import com.example.biddingwar.database.User
import com.example.biddingwar.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

@Service
@Transactional
class UserService(val repository: UserRepository) {

    val bcrypt = BCryptPasswordEncoder(11)

    fun signUp(user: User): ResponseEntity<String> {
        val signedUpUsers: User? = repository.findByEmail(user.email)

        if (signedUpUsers != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DUPLICATE_USER_EMAIL")
            }

        if (user.pwd.length < 4) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Minimum password length is 4")
        }

        user.pwd = bcrypt.encode(user.password)
        repository.save(user)

        return ResponseEntity.ok().body("OK")
    }

    fun signIn(userRequest: User, request: HttpServletRequest): ResponseEntity<String> {
        // 이메일을 통한 유저 조회
        val user = repository.findByEmail(userRequest.email)

        if (user != null) {
            // 비밀번호 검증
            if (bcrypt.matches(userRequest.pwd, user.password)) {
                val session = request.session
                session.setAttribute("session", user.id)

                return ResponseEntity.ok().body("${user.id}")
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED")
    }

    fun getAll() = repository.findAll()
}