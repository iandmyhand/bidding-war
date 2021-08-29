package kr.co.peoplefund.biddingWar.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Session (@Column(unique = true) var key: String, var userId: Long?){
    @Id
    @GeneratedValue
    var id: Long? = null
}