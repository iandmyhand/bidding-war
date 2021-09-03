package kr.co.peoplefund.biddingWar.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class User(
    @JsonIgnore var email: String,
    @JsonIgnore var password: String
    ) {
    @Id
    @GeneratedValue
    var id: Long? = null
}