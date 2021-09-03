package kr.co.peoplefund.biddingWar.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Product(@ManyToOne var owner: User, var name: String, var price: Long, var minimumBiddingPrice: Long) {
    @Id
    @GeneratedValue
    var id: Long? = null

}