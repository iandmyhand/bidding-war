package com.study.biddingwar.domain.entity

import jdk.jfr.Description
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "goods")
class Goods {

    constructor(goodsName: String,
                goodsPrice: Int,
                goodsContent: String,
                goodsCategory: String) {
        goodsName.also { this.goodsName = it}
        goodsPrice.also { this.goodsPrice = it }
        goodsContent.also { this.goodsContent = it }
        goodsCategory.also { this.goodsCategory = it }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_id")
    @Description("상품 ID - pk , not null , auto incre~")
    val id: Long?=null

    @Column(name = "goods_name")
    @Description("상품 이름")
    var goodsName: String private set

    @Column(name = "goods_price")
    @Description("상품 가격")
    var goodsPrice: Int private set

    @Column(name = "goods_category")
    @Description("상품 카테고리")
    // @TODO: 이건 나중에 별도 테이블로 관리할 것 --> 일단 A, B 정도로만 구분..
    var goodsCategory: String private set

    @Column(name = "goods_content")
    @Description("상품 본문내용")
    var goodsContent: String private set

    @Column(name = "create_date")
    @CreationTimestamp
    @Description("상품 생성일")
    var createDate: Instant?=Instant.now()

    @Column(name = "update_date")
    @UpdateTimestamp
    @Description("상품 수정일")
    var updateDate: Instant?=Instant.now()

    // ------------ modify 용 setter 생성 -------------- //
    fun setGoodsName(goodsName: String) {
        if (!goodsName.isNullOrBlank()) {
            this.goodsName = goodsName
        }
    }

    fun setGoodsPrice(goodsPrice: Int) {
        if (goodsPrice > 0) {
            this.goodsPrice = goodsPrice
        }
    }

    fun setGoodsContent(goodsContent: String) {
        if (!goodsContent.isNullOrBlank()) {
            this.goodsContent = goodsContent
        }
    }

    fun setGoodsCategory(goodsCategory: String) {
        if (!goodsCategory.isNullOrBlank()) {
            this.goodsCategory = goodsCategory
        }
    }
}