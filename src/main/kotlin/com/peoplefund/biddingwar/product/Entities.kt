package com.peoplefund.biddingwar.product

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
class Product(
    var name: String,
    var amount: Long,
    var description: String? = null,
    @Id @GeneratedValue var id: Long? = null)
