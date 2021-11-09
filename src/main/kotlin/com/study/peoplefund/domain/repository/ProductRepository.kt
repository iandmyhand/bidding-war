package com.study.peoplefund.domain.repository

import com.study.peoplefund.domain.Product
import com.study.peoplefund.domain.vo.BiddingStatus
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ProductRepository : JpaRepository<Product, Long?> {
    fun findByEndDateTimeBeforeAndStatusEquals(endDateTime: LocalDateTime, status: BiddingStatus): List<Product>
}