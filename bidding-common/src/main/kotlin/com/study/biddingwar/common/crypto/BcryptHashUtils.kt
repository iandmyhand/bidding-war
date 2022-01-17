package com.study.biddingwar.common.crypto

import org.springframework.security.crypto.bcrypt.BCrypt

class BcryptHashUtils {
    companion object{
        fun hashed(plainPw:String): String{
            return BCrypt.hashpw(plainPw, BCrypt.gensalt())
        }

        fun checkPw(plainPw:String, userPw:String): Boolean {
            return BCrypt.checkpw(plainPw, userPw)
        }
    }
}