package com.example.biddingwar.repository

import com.example.biddingwar.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findUserByUserIdEquals(userId: String): User
    fun findUserByUserNameEquals(userName: String): User
    fun findUserByUserIdEqualsAndUserPwEquals(userId: String, userPw: String): User
}