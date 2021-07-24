package com.example.biddingwar.entity

import java.time.LocalDateTime
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import javax.persistence.*

enum class BasicState {
    ACTIVE, INACTIVE, DELETE
}

abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreatedDate
    val createdTime: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    val updatedTime: LocalDateTime = LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    var activeState: BasicState = BasicState.ACTIVE
}