package com.study.biddingwar.service

import com.study.biddingwar.common.crypto.BcryptHashUtils
import com.study.biddingwar.domain.entity.User
import com.study.biddingwar.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import javax.transaction.NotSupportedException

@Service
class UserService(private val userRepository: UserRepository):UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        throw NotSupportedException("this method not support, use loadUserByUserId")
    }

    fun loadUserByUserId(userId: String): User{
        return userRepository.findByUserName( userId!!)
//            .let(
//            throw NoSuchElementException("user not found"))
    }

    fun createUser(userName:String, password:String):User{
        val user = User(userName= userName, userPassword = BcryptHashUtils.hashed(password))
        return userRepository.save(user)
    }

    fun refreshLoginCount(user: User){
        userRepository.save(user)
    }

}