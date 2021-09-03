package kr.co.peoplefund.biddingWar.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class Bid(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    @ManyToOne var bidder: User,
    var biddingPrice: Long,
    @ManyToOne(fetch = FetchType.LAZY) var product: Product
    )
