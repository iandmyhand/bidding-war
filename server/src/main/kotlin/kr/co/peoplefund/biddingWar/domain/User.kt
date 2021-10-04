package kr.co.peoplefund.biddingWar.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class User(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    @JsonIgnore var email: String,
    @JsonIgnore var password: String
    )
