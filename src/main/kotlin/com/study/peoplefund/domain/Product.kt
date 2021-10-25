package com.study.peoplefund.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(
        @Id
        @GeneratedValue
        var firstOwnerId: Long? = null,
        var id: Long? = null,

        var name: String,

        var minPrice: Long
)
