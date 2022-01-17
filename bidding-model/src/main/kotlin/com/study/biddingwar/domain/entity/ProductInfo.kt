package com.study.biddingwar.domain.entity

import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate

import java.time.Instant
import javax.persistence.*

@Entity
@Table(name = "product_info")
class ProductInfo{
    constructor(productGroup: String,
                productName: String,
                productPrice:Int,
                productDesc:String){
        productGroup.also { this.productGroup = it }
        productName.also { this.productName = it }
        productPrice.also { this.productPrice = it }
        productDesc.also { this.productDesc = it }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long?=null

    @Column(name = "product_group")
    var productGroup:String
        private set

    @Column(name = "product_name")
    var productName:String
        private set

    @Column(name = "product_price")
    var productPrice:Int=0
        private set

    @Column(name = "product_desc")
    var productDesc:String? = ""
        private set

    @Column(name = "created_at")
    @CreatedDate
    val createdAt:Instant?=Instant.now()

    @Column(name = "updated_at")
    @UpdateTimestamp
    val updatedAt:Instant?=Instant.now()

    fun setProductGroup(value:String){
        if(!value.isNullOrBlank()){
            this.productGroup = value
        }
    }

    fun setProductName(value:String){
        if(!value.isNullOrBlank())
            this.productName = value
    }

    fun setProductDesc(value:String){
        if(!value.isNullOrBlank())
            this.productDesc = value
    }

    fun setProductPrice(value:Int){
        if(value>0)
            this.productPrice = value
    }
}
