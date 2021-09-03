package kr.co.peoplefund.biddingWar.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Bid(var userId: Long, var biddingPrice: Long) {
    @Id
    @GeneratedValue
    var id: Long? = null
    var productId: Long? = null
}