package com.study.peoplefund.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Bidding(
        @Id
        @GeneratedValue
        var id: Long? = null,

        @ManyToOne
        var user: User,

        @ManyToOne
        var product: Product,

        var price: Long
)