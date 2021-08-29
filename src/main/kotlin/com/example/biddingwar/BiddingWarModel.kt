package com.example.biddingwar

import org.hibernate.annotations.CreationTimestamp
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue val id: Long,
    var name: String,
    var price: Int = 0,
    val description: String,
    var minimumPrice: Int = 0
)

@Entity
data class User(
    @Id @GeneratedValue val id: Long,
    var email: String,
    var pwd: String,
    var session: Pair<Long, String>? = null,

    @CreationTimestamp
    var createDt: LocalDateTime = LocalDateTime.now()
): UserDetails {

    override fun getPassword(): String = pwd

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        var authorities = mutableListOf<GrantedAuthority>()

        authorities.add(SimpleGrantedAuthority("ROLE_MEMBER"))

        return authorities
    }
}

@Entity
data class Bid(
    @Id @GeneratedValue
    val id: Long,
    var productId: Long,
    var userId: Long,
    var biddingPrice: Int = 0,
)