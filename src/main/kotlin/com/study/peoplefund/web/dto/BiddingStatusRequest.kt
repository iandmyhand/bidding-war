package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.vo.BiddingStatus

data class BiddingStatusRequest(
    val status: String
) {
    fun getBiddingStatus(): BiddingStatus {
        return BiddingStatus.of(status)
    }
}