package com.study.biddingwar.repository

import com.study.biddingwar.domain.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User, Long> {
}