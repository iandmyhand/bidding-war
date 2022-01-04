package com.example.biddingwar.account

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import java.util.stream.Collectors
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
data class Account(
    @Id
    @GeneratedValue
    var id: Long? = null,

    @Column(unique = true)
    var email: String,

    @field:Size(min = 4)
    var password: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    var roles: MutableSet<AccountRole> = mutableSetOf(AccountRole.ADMIN, AccountRole.USER),
){
    fun getAuthorities(): User{
        return User(
            this.email,
            this.password,
            this.roles.stream().map{ role -> SimpleGrantedAuthority("ROLE_$role") }.collect(Collectors.toSet()))
    }
}
