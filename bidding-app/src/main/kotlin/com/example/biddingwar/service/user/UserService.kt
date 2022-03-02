package com.example.biddingwar.service.user

import com.example.biddingwar.database.dto.UserSignUpRequestDto
import com.example.biddingwar.database.dto.UserSignUpResponseDto
import com.example.biddingwar.database.entity.User
import com.example.biddingwar.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

@Service
@Transactional
class UserService(val repository: UserRepository) {
    val bcrypt = BCryptPasswordEncoder(11)

    fun signUp(userSignUpRequestDto: UserSignUpRequestDto): UserSignUpResponseDto {
        val signedUpUsers: User? = repository.findByEmail(userSignUpRequestDto.email)

        if (signedUpUsers != null) {
                throw Exception("Duplicated User Email")
            }

        if (userSignUpRequestDto.password.length < 4) {
            throw Exception("Minimum password length is 4")
        }

        val user = createUser(userEmail = userSignUpRequestDto.email, userPassword = userSignUpRequestDto.password)
        return UserSignUpResponseDto(id = user.id!!, email = user.email, createdAt = user.createDt)
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

    fun createUser(userEmail: String, userPassword: String): User {
        return repository.save(User(email = userEmail, pwd = bcrypt.encode(userPassword)))

    }
}