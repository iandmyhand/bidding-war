package com.study.biddingwar.domain.entity

import org.springframework.data.annotation.CreatedDate
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name="bidding_info", indexes = [Index(name = "uidx_product_user",columnList = "product_id, user_id", unique = true)])
class BiddingInfo {

    constructor(productId:Long, userId:Long, biddingPrice:Long){
        this.productId=productId
        this.userId = userId
        this.biddingPrice = biddingPrice
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null

    @Column(name = "product_id")
    var productId:Long = 0

    @Column(name="user_id")
    var userId:Long = 0

    @Column(name="bidding_price")
    var biddingPrice:Long = 0

    @CreatedDate
    @Column(name="create_at")
    val createAt:Instant = Instant.now()

}