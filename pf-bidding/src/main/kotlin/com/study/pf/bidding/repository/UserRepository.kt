package com.study.pf.bidding.repository


import com.study.pf.bidding.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository:JpaRepository<User, Long> {

    fun findByUserId(userId: String): User?
}