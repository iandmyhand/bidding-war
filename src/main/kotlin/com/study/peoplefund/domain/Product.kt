package com.study.peoplefund.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(
        @Id
        @GeneratedValue
        var id: Long? = null,

        var name: String,

        var minPrice: Long,

        var currentPrice: Long
)