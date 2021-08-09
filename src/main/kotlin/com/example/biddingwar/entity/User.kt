package com.example.biddingwar.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name="USER")
class User (
    @Id
    @Column(name="number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val number: Long? = null,

    @JsonIgnore
    @Column(name="user_id", length=20, unique=true)
    val userId: String,

    @JsonIgnore
    @Column(name="user_pw", length=30)
    val userPw: String,

    @Column(name="user_name", length=20, unique=true)
    val userName: String,

    @JsonIgnore
    @Column(name="authority")
    val authority: String,

    @JsonIgnore
    @Column(name="activated")
    val activated: Boolean=true,

    @CreatedDate
    val createdTime: LocalDateTime?,
) {}
