package com.example.biddingwar.service.user

import com.example.biddingwar.database.User
import com.example.biddingwar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserCustomService : UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(userEmail: String?): User? {
        return userRepository.findByEmail(userEmail)
    }

}