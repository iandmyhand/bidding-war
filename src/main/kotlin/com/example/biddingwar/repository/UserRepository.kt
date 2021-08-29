package com.example.biddingwar.repository

import com.example.biddingwar.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUserId(userId: String): User?
    fun findUserByUserName(userName: String): User?
    fun findUserByUserIdAndUserPw(userId: String, userPw: String): User?
}