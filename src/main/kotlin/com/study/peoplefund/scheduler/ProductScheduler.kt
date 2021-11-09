package com.study.peoplefund.scheduler

import com.study.peoplefund.domain.repository.BiddingRepository
import com.study.peoplefund.domain.repository.ProductRepository
import com.study.peoplefund.domain.vo.BiddingStatus
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ProductScheduler(
    val productRepository: ProductRepository,
    val biddingRepository: BiddingRepository
) {

    @Scheduled(fixedDelay = 1000)
    fun closeBiddingByEndDateTime() {
        val products = productRepository.findByEndDateTimeBeforeAndStatusEquals(
            endDateTime = LocalDateTime.now(),
            status = BiddingStatus.IN_PROGRESS
        )

        for (product in products) {
            when (biddingRepository.existsByProduct(product)) {
                true -> product.status = BiddingStatus.SUCCESS
                false -> product.status = BiddingStatus.FAILURE
            }
        }

        productRepository.saveAll(products)

        val now = LocalDateTime.now()
        println("[${now.year}-${now.monthValue}-${now.dayOfMonth} ${now.hour}:${now.minute}:${now.second}] " +
            "총 ${products.size}개의 입찰을 종료했습니다.")
    }
}