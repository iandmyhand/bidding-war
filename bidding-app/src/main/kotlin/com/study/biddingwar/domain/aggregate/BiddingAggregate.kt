package com.study.biddingwar.domain.aggregate

import com.study.biddingwar.domain.entity.ProductInfo
import com.study.biddingwar.domain.entity.User
import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name="bidding_info")
class BiddingAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null

    @Column(name="bidding_price")
    var biddingPrice:Long = 0

    @CreatedDate
    @Column(name="create_at")
    val createAt: Instant = Instant.now ()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    val user:User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    val productInfo:ProductInfo? = null
}