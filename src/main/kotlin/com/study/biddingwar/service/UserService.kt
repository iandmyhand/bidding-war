package com.study.biddingwar.service

import com.study.biddingwar.common.crypto.PasswordBCrypto
import com.study.biddingwar.domain.dto.SignUpDto
import com.study.biddingwar.domain.entity.User
import com.study.biddingwar.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository): UserDetailsService {

    // Spring-security : BCrypto 활용하여 패스워드 암호화 진행
    fun createUser(signUpDto: SignUpDto): User {
        val user = User(
                signUpDto.userId,
                signUpDto.userName,
                signUpDto.userNick,
                PasswordBCrypto.hashPassword(signUpDto.userPassword))

        return userRepository.save(user);
    }

    override fun loadUserByUsername(authId: String): User {
        return userRepository.findByUserId(authId)?.let {
            User(it.userId,
                it.userName,
                it.userNick,
                it.userPassword)
        } ?: throw UsernameNotFoundException("USER : $authId is Not Found")
    }

}