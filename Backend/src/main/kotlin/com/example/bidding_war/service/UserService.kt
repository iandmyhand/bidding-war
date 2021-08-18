package com.example.bidding_war.service

import com.example.bidding_war.model.Session
import com.example.bidding_war.model.User
import com.example.bidding_war.repository.SessionRepository
import com.example.bidding_war.repository.UserRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(val userRepository: UserRepository, val sessionRepository: SessionRepository) {

    fun register(user: User) = userRepository.save(user)

    fun findAll() = userRepository.findAll()

    fun findById(id: Long) = userRepository.findById(id)

    fun update(user: User) = userRepository.save(user)

    fun delete(id: Long) = userRepository.deleteById(id)

    @Transactional
    fun signIn(user: User): ResponseEntity<String> {
        val user = userRepository.findByEmailAndPassword(
            account = user.email,
            password = user.password
        ).orElseThrow()

        val session = Session(
            key = UUID.randomUUID().toString(),
            email = user.email
        )

        sessionRepository.save(session)
        return ResponseEntity.ok(
            "login complete $user")
    }

    fun signUp(user: User): ResponseEntity<String> {
        userRepository.save(user).id!!
        return ResponseEntity.ok(
            "register complete $user")
    }


}