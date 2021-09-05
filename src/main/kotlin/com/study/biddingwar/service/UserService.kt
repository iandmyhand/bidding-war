package com.study.biddingwar.service

import com.study.biddingwar.common.crypto.BcryptHashUtils
import com.study.biddingwar.domain.entity.User
import com.study.biddingwar.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository):UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        TODO("Not yet implemented")
    }

    fun createUser(userName:String, password:String):User{
        val user = User(userName= userName, userPassword = BcryptHashUtils.hashed(password))
        return userRepository.save(user)
    }
}