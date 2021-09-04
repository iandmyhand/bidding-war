package com.example.biddingwar.service.user

import com.example.biddingwar.database.User
import com.example.biddingwar.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

@Service
@Transactional
class UserService(val repository: UserRepository) {

    val bcrypt = BCryptPasswordEncoder(11)

    fun signUP(user: User): Boolean {
        val signedUpUsers: MutableIterable<User> = getAll()

        for(signedUpUser in signedUpUsers) {
            if (signedUpUser.email == user.email) {
                return false
            }
        }

        user.pwd = bcrypt.encode(user.password)
        repository.save(user)

        return true
    }

    fun signIn(userRequest: User, request: HttpServletRequest): User? {
        // 이메일을 통한 유저 조회
        val user = repository.findByEmail(userRequest.email)

        if (user != null) {
            // 비밀번호 검증
            if (bcrypt.matches(userRequest.pwd, user?.password)) {
                val session = request.session
                session.setAttribute("session", user.id)

                return user
            }
        }

        return null
    }

    fun getAll() = repository.findAll()
}