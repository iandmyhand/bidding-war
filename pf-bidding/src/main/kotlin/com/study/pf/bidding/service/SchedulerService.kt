package com.study.pf.bidding.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled

@EnableScheduling
@Configuration
class SchedulerService(private val biddingService: BiddingService,
                       @Value("\${spring.task.expiredBiddingPeriod}") val expiredBiddingPeriod: String) {

    // @TODO: yml 값으로 변경필요
    @Scheduled(cron = "0 * * * * *")
    fun expiredBiddingPeriod() {
        biddingService.setAutionStatus()
    }
}