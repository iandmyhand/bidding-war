package com.study.peoplefund.domain.repository

import com.study.peoplefund.domain.Session
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SessionRepository : JpaRepository<Session, Long?> {
    fun findByToken(token: String): Optional<Session>
}
