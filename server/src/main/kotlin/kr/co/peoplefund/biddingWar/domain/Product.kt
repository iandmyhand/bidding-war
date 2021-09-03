package kr.co.peoplefund.biddingWar.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(var name: String, var price: Long, var minimumBiddingPrice: Long) {
    @Id
    @GeneratedValue
    var id: Long? = null
}