package com.study.peoplefund.domain

import com.study.peoplefund.domain.vo.BiddingStatus
import com.study.peoplefund.domain.vo.BiddingStatusConverter
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Product(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    var seller: User,

    var name: String,

    var minPrice: Long,

    @Convert(converter = BiddingStatusConverter::class)
    var status: BiddingStatus = BiddingStatus.IN_PROGRESS,

    var endDateTime: LocalDateTime = getDefaultEndTime()
) {
    companion object {
        private const val DEFAULT_END_TIME_DAYS: Long = 1

        fun getDefaultEndTime(): LocalDateTime {
            return LocalDateTime.now().plusDays(DEFAULT_END_TIME_DAYS)
        }
    }
}