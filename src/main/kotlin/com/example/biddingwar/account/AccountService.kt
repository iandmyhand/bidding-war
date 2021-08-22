package com.example.biddingwar.account

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountService(@Autowired private val accountRepository: AccountRepository,
                     @Autowired private val passwordEncoder: PasswordEncoder): UserDetailsService {
    fun saveAccount(account: Account): Account {
        // password
        account.password = this.passwordEncoder.encode(account.password)
        return accountRepository.save(account)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        return accountRepository.findByEmail(username)?.getAuthorities()
            ?: throw UsernameNotFoundException("$username Can Not Found")
    }
}