package com.example.biddingwar.dto

import com.example.biddingwar.entity.User
import java.security.MessageDigest

class UserSignInForm(val userId: String, val userPw: String, val userName:String) {
//    @Autowired
//    val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    fun crypto(ss: String): String{
        val sha = MessageDigest.getInstance("SHA-256")
        val hexa = sha.digest(userPw.toByteArray())
        return hexa.fold("", {str, it -> str + "%02x".format(it)})
    }

    fun toEntity(): User {
        val encoded_user_pw : String = crypto(userPw)

//        val encoded_user_pw : String = MessageDigest
//            .getInstance("SHA-256")
//            .digest(userPw.toByteArray()).toString()
//        val encoded_user_pw :     String = passwordEncoder.encode(userPw)

        return User(null,
            userId = userId,
            userPw = encoded_user_pw,
            userName = userName,
            authority = "USER",
        )
    }
}
