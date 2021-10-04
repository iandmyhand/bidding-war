package com.study.peoplefund.domain

import com.study.peoplefund.domain.vo.BiddingStatus
import com.study.peoplefund.domain.vo.BiddingStatusConverter
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
    var status: BiddingStatus = BiddingStatus.IN_PROGRESS
)