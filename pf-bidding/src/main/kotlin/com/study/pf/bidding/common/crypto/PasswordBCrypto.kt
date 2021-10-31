package com.study.pf.bidding.common.crypto

import org.springframework.security.crypto.bcrypt.BCrypt

/**
 * PasswordEncoderFactories.createDelegatingPasswordEncoder() 사용할까 하다가 아래의 BCrypto로 활용
 */
class PasswordBCrypto {
    companion object {

        fun hashPassword(plainPassword: String): String {
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt())
        }

        fun checkPassword(plainPassword: String, userPassword: String): Boolean {
            return BCrypt.checkpw(plainPassword, userPassword)
        }
    }
}