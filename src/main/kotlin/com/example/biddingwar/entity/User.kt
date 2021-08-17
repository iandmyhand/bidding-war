package com.example.biddingwar.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Getter
@Setter
class User (
    @Id
    @GeneratedValue
    val number: Long? = null,

    @JsonIgnore
    @Column(name="user_id", length=20, unique=true)
    val userId: String,

    @JsonIgnore
    @Column(name="user_pw", length=200)
    val userPw: String,

    @Column(name="user_name", length=20, unique=true)
    val userName: String,

    @JsonIgnore
    @Column(name="authority")
    val authority: String,
) {}
