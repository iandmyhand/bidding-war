package kr.co.peoplefund.biddingWar.domain.repository

import kr.co.peoplefund.biddingWar.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long?> {
}