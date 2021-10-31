package com.study.pf.bidding.domain.entity

import jdk.jfr.Description
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.persistence.*

@Entity
@Table(name = "bidding")
class Bidding {

    constructor(biddingGoods: Goods,
                biddingUser: User,
                biddingStartDt: Instant,
                biddingEndDt: Instant,
                biddingPrice: Int
    ) {
        biddingGoods.also { this.biddingGoods = it }
        biddingUser.also { this.biddingUser = it }
        biddingStartDt.also { this.biddingStartDt = it }
        biddingEndDt.also { this.biddingEndDt = it }
        biddingPrice.also { this.biddingPrice = it }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bidding_id")
    @Description("입찰 ID - pk , not null , auto incre~~~")
    val id: Long?=null

    @Description("입찰 상품 정보 // fk=>번호=id=pk")
    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id")
    val biddingGoods: Goods

    @Description("현재 입찰에 참여한 사용자정보 // fk=>user_id")
    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    var biddingUser: User
        private set

    @Column(name = "bidding_price")
    var biddingPrice: Int
        private set

    @Column(name = "bidding_start_dt")
    var biddingStartDt: Instant?=Instant.now()
    
    // 기본으로는 종료date 3일 연장 --> 최초 설정이후로는 변경할 수 없음!
    @Column(name = "bidding_end_dt")
    var biddingEndDt: Instant?=Instant.now().plus(3, ChronoUnit.DAYS)

    @Column(name = "is_finishing")
    var isFinishing: Boolean = false
        private set

    // ------------ modify 용 setter 생성 -------------- //
    fun setFinishing(isFinishing: Boolean) {
        this.isFinishing = isFinishing
    }

    fun setBiddingPrice(biddingPrice: Int) {
        if (biddingPrice > 0) {
            this.biddingPrice = biddingPrice
        }
    }

    fun setBiddingUser(biddingUser: User) {
        if (biddingUser != null) {
            this.biddingUser = biddingUser
        }
    }
}