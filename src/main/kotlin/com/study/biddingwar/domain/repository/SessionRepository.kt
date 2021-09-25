package com.study.biddingwar.domain.repository

import com.study.biddingwar.domain.Session
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SessionRepository : JpaRepository<Session, Long?> {
	fun findByKey(key: String): Optional<Session>
}
