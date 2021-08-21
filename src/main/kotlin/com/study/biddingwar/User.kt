package com.study.biddingwar

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    @Id
    @GeneratedValue
    val id: Long? = null,

    private var name: String,

    private var password: String

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = arrayListOf(SimpleGrantedAuthority("USER"))
    override fun getPassword() = password
    override fun getUsername() = name
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}