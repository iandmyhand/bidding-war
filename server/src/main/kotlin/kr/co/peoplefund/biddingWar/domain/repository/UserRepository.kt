package kr.co.peoplefund.biddingWar.domain.repository

import kr.co.peoplefund.biddingWar.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long?> {
}