package kr.co.peoplefund.biddingWar.domain.repository

import kr.co.peoplefund.biddingWar.domain.Session
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SessionRepository : JpaRepository<Session, Long?> {
	fun findByKey(key: String): Optional<Session>
}