package kr.co.peoplefund.biddingWar.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class Bid(
    var bidderId: Long,
    var biddingPrice: Long,
    @ManyToOne(fetch = FetchType.LAZY) var product: Product
    ) {
    @Id
    @GeneratedValue
    var id: Long? = null
}