package com.study.peoplefund.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Product(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @ManyToOne
    var seller: User,

    var name: String,

    var minPrice: Long
)