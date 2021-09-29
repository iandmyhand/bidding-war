package com.study.biddingwar.domain.entity

import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.Instant
import javax.persistence.*

/**
 * UserDetails 반쪽..구현
 */
@Entity
@Table(name = "user", indexes = [
    Index(name = "uid_userid", columnList = "user_id", unique = true)
])
class User: UserDetails {

    constructor(userId: String,
                userName: String,
                userNick: String,
                userPassword: String){
        this.userId = userId
        this.userName = userName
        this.userNick = userNick
        this.userPassword= userPassword
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?=null

    @Column(name = "user_id")
    var userId: String = ""

    @Column(name="user_name")
    var userName: String = ""

    @Column(name="user_password")
    var userPassword: String = ""

    @Column(name="user_nick")
    var userNick: String = ""

    @CreatedDate
    @Column(name="create_dt")
    var createDt: Instant = Instant.now()

    @UpdateTimestamp
    @Column(name="update_dt")
    var updateDt: Instant? = null

    /**************************************/

    fun modifyUserPassword(newPassword: String) {
        this.userPassword = newPassword
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun getPassword(): String {
        return this.userPassword
    }

    override fun getUsername(): String {
        return this.userId
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