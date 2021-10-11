package com.peoplefund.biddingwar.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class UserService(@Autowired val userRepository: UserRepository, @Autowired val passwordEncoder: PasswordEncoder) {
    fun signUp(signupUser: UserSignupRequest): User {
        if (userRepository.existsUserByUserId(signupUser.userId)) {
            throw AlreadyExistUserException()
        }

        val encodedPassword = passwordEncoder.encode(signupUser.password)

        return userRepository.save(User(signupUser.userId, encodedPassword))
    }

    fun signIn(userRequestBody: UserSignupRequest): User {
        val findUser = userRepository
            .findByUserId(userRequestBody.userId)
            .orElseThrow { NoMatchedSignInException() }

        if (!passwordEncoder.matches(userRequestBody.password, findUser.password)) {
            throw NoMatchedSignInException()
        }

        return findUser
    }
}