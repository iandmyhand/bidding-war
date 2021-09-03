package kr.co.peoplefund.biddingWar.domain

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.*

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator::class, property = "id")
class Product(
    @ManyToOne var owner: User,
    var name: String,
    var price: Long,
    var minimumBiddingPrice: Long,
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product") var bids: MutableList<Bid>
) {
    @Id
    @GeneratedValue
    var id: Long? = null

}