package com.study.peoplefund.domain.vo

import javax.persistence.AttributeConverter

class BiddingStatusConverter : AttributeConverter<BiddingStatus, String> {
    override fun convertToDatabaseColumn(biddingStatus: BiddingStatus): String {
        return biddingStatus.getValue()
    }

    override fun convertToEntityAttribute(dbData: String): BiddingStatus {
        return BiddingStatus.of(dbData)
    }
}