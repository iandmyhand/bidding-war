package com.example.biddingwar.dto

import com.example.biddingwar.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDateTime

class UserSignInForm(val userId: String, val userPw: String, val userName:String,
                     val createTime: LocalDateTime = LocalDateTime.now()) {
    @Autowired
    val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    fun toEntity(): User {

        return User(null, userId,
            userPw = passwordEncoder.encode(userPw),
            userName = userName,
            authority = "user",
            activated = true,
            createdTime = createTime
        )
    }
}
