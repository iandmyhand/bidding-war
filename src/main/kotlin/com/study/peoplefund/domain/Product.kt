package com.study.peoplefund.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Product(var name: String, var price: Long) {
    @Id
    @GeneratedValue
    var id: Long? = null
}