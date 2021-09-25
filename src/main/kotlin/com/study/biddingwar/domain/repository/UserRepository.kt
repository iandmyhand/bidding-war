package com.study.biddingwar.domain.repository

import com.study.biddingwar.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository : JpaRepository<User, Long?> {
    fun findByEmailAndPassword(email: String, password: String): Optional<User>
}
