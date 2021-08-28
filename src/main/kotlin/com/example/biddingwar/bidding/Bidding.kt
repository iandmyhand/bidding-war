package com.example.biddingwar.bidding

import com.example.biddingwar.account.Account
import com.example.biddingwar.product.Product
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Bidding (
    @Id
    @GeneratedValue
    val id: Long?,

    @ManyToOne
//    @JoinColumn(foreignKey = ForeignKey(ConstraintMode.NO_CONSTRAINT))
    var product: Product?,

    @ManyToOne
    var account: Account?,

    var price: Int,
)