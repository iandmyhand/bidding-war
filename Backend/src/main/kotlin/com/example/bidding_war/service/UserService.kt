package com.example.bidding_war.service

import com.example.bidding_war.model.User
import com.example.bidding_war.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun register(user: User) = userRepository.save(user)

    fun findAll() = userRepository.findAll()

    fun findById(id: Long) = userRepository.findById(id)

    fun update(user: User) = userRepository.save(user)

    fun delete(id: Long) = userRepository.deleteById(id)

    fun signIn(user: User): Boolean {
        val signedInUsers: MutableIterable<User> = findAll()

        for(signedInUser in signedInUsers) {
            if (signedInUser.email == user.email && signedInUser.password == user.password) {
                return true
            }
        }
        return false
    }

    fun signUp(user: User): Boolean {
        val signedUpUsers: MutableIterable<User> = findAll()

        for(signedUpUser in signedUpUsers) {
            if (signedUpUser.email == user.email) {
                return false
            }
        }
        return true
    }


}