package com.example.biddingwar.repository

import com.example.biddingwar.database.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long> {
    fun findByEmail(email: String?): User?
}