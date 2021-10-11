package com.peoplefund.biddingwar.users

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(
    var userId: String,
    var password: String,

    @CreationTimestamp
    var createAt: LocalDateTime = LocalDateTime.now(),

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: Long? = null
)
