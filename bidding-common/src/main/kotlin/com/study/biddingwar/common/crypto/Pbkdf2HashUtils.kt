package com.study.biddingwar.common.crypto

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm


class Pbkdf2HashUtils {
    companion object{

        fun hashed(plainPw:String): String{
            val pbkdf2PasswordEncoder = Pbkdf2PasswordEncoder("",15000, 256)
            pbkdf2PasswordEncoder.setAlgorithm(SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)
            return pbkdf2PasswordEncoder.encode(plainPw)
        }

        fun checkPw(plainPw:String, userPw:String): Boolean {
            val pbkdf2PasswordEncoder = Pbkdf2PasswordEncoder("",15000, 256)
            pbkdf2PasswordEncoder.setAlgorithm(SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256)
            return pbkdf2PasswordEncoder.matches(plainPw, userPw)
        }
    }
}