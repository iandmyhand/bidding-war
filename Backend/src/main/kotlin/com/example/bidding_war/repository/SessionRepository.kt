package com.example.bidding_war.repository


import com.example.bidding_war.model.Session
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface SessionRepository : JpaRepository<Session, Long?> {
    fun findByToken(token: String): Optional<Session>
    fun findByUserId(userId: Long): Optional<Session>
}