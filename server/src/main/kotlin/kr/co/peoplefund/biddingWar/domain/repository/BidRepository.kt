package kr.co.peoplefund.biddingWar.domain.repository

import kr.co.peoplefund.biddingWar.domain.Bid
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BidRepository : JpaRepository<Bid, Long?> {
    fun findByProductId(productId: Long): List<Bid>
}