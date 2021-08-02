package com.peoplefund.biddingwar.product

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class Product(
    var name: String,
    var amount: Long,
    var description: String? = null,
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) var id: Long? = null)
