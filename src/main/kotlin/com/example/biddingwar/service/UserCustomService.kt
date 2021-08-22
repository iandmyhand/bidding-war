package com.example.biddingwar.service

import com.example.biddingwar.BiddingWarUserRepository
import com.example.biddingwar.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserCustomService : UserDetailsService {
    @Autowired
    lateinit var userRepository: BiddingWarUserRepository

    override fun loadUserByUsername(username: String?): User? {
        return userRepository.findByName(username)
    }

}