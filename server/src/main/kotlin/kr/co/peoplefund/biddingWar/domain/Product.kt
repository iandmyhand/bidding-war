package kr.co.peoplefund.biddingWar.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import java.util.*
import javax.persistence.*

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class Product(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    @ManyToOne var owner: User,
    var name: String,
    var price: Long,
    var minimumBiddingPrice: Long,
    var biddingEndTime: Date,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product") var bids: MutableList<Bid>,
    @ManyToOne(fetch = FetchType.LAZY) var winningBid: Bid? = null
)
