package com.study.biddingwar.repository

import com.study.biddingwar.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository:JpaRepository<User, Long> {

    fun findByUserId(userId: String): User?
}