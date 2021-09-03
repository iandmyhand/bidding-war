package kr.co.peoplefund.biddingWar.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class User(var email: String, var password: String, @OneToMany val products: MutableList<Product>? = null) {
    @Id
    @GeneratedValue
    var id: Long? = null
}