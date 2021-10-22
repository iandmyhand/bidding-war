package kr.co.peoplefund.biddingWar.domain.repository

import kr.co.peoplefund.biddingWar.domain.Bid
import kr.co.peoplefund.biddingWar.domain.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductRepository : JpaRepository<Product, Long?> {
    fun findByWinningBidIsNullAndBidsExistsBiddingEndTimeLessThanEqual(biddingEndTime: Date): List<Product>
}