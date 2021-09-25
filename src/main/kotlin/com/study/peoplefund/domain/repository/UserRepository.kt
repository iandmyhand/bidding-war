package com.study.peoplefund.domain.repository

import com.study.peoplefund.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long?> {
    fun findByAccountAndPassword(account: String, password: String): Optional<User>

    fun findByAccount(account: String): Optional<User>
}