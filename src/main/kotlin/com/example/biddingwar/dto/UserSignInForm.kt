package com.example.biddingwar.dto

import com.example.biddingwar.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.MessageDigest

class UserSignInForm(val userId: String, val userPw: String, val userName:String) {
    @Autowired
    val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    fun crypto(ss: String): String{
        val sha = MessageDigest.getInstance("SHA-256")
        val hexa = sha.digest(userPw.toByteArray())
        return hexa.fold("", {str, it -> str + "%02x".format(it)})
    }

    fun toEntity(): User {
        val encodedUserPw : String = crypto(userPw)
//        val encodedUserPw : String = MessageDigest
//            .getInstance("SHA-256")
//            .digest(userPw.toByteArray()).toString()
//        val encodedUserPw :     String = passwordEncoder.encode(userPw)

        return User(null,
            userId = userId,
            userPw = encodedUserPw,
            userName = userName,
            authority = "USER",
        )
    }
}
