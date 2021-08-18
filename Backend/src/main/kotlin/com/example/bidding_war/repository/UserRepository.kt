package com.example.bidding_war.repository

import com.example.bidding_war.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : JpaRepository<User, Long?> {
    fun findByEmailAndPassword(account: String?, password: String?): Optional<User>
}