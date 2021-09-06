package com.study.biddingwar.domain.entity

import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import javax.persistence.*

@Entity
@Table(name="user", indexes = [Index(name = "uidx_username",columnList = "user_name", unique = true)])
class User:UserDetails {

    constructor(userName:String, userPassword:String, authorities:String?="USER_ROLE"){
        this.userName = userName
        this.userPassword= userPassword
        this.authorities = authorities!!
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null

    @Column(name="user_name")
    var userName:String = ""
    @Column(name="user_password")
    var userPassword:String = ""
    @Column(name="user_authorities")
    var authorities:String = ""
    @Column(name="user_failed_cnt")
    var failedCnt:Int = 0
    @Column(name="user_locked")
    var locked:Boolean = false
    @CreatedDate
    @Column(name="created_at")
    var createdAt:Instant = Instant.now()
    @UpdateTimestamp
    @Column(name="updated_at")
    var updatedAt:Instant? = null

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        return this.userPassword
    }

    override fun getUsername(): String {
        return this.username
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}