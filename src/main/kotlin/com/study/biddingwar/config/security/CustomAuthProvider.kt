package com.study.biddingwar.config.security

import com.study.biddingwar.common.crypto.BcryptHashUtils
import com.study.biddingwar.domain.entity.User
import com.study.biddingwar.service.UserService
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthProvider(private val userService: UserService):AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication? {
        val userId = authentication.name //userId
        val password = authentication.credentials.toString() //userPassword

        val user:User = userService.loadUserByUserId(userId)
        val roles = user.getAuthorities()
        var details = HashMap<String, Any>()

        //password valid check
        if(!BcryptHashUtils.checkPw(password, user.password)){
            user.failedCnt = user.failedCnt++
            userService.refreshLoginCount(user)
            throw RuntimeException("password invalid")
        }
        //user login 성공하면 count 0으로 초기화
        user.failedCnt = 0
        userService.refreshLoginCount(user)

        // user정보 디테일에 넣음.
        details.put("user", user)

        if(roles != null){
            val token = UsernamePasswordAuthenticationToken(user.id, password, roles)
            token.details = details
            return token
        }

        return null
    }

//    override fun supports(authentication: Class<*>?): Boolean {
//        if (authentication != null) {
//            return authentication.equals(UsernamePasswordAuthenticationToken::class)
//        }
//        return false
//    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }
}