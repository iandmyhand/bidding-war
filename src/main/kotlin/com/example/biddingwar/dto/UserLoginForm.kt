package com.example.biddingwar.dto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.MessageDigest

class UserLoginForm(val userId: String, val userPw: String) {

    @Autowired
    val passwordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()

    fun crypto(ss: String): String{
        val sha = MessageDigest.getInstance("SHA-256")
        val hexa = sha.digest(userPw.toByteArray())
        return hexa.fold("", {str, it -> str + "%02x".format(it)})
    }

    fun toEntity(): Pair<String, String> {
        val encoded_user_pw : String = crypto(userPw)
//        val encoded_user_pw : String = passwordEncoder.encode(userPw)


        return Pair(userId, encoded_user_pw)
    }
}