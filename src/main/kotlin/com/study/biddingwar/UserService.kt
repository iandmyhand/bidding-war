package com.study.biddingwar

import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service



@Service
class UserService(val repository: UserRepository) : UserDetailsService {
    fun save(userDto: UserDto): Long {
        val encoder = BCryptPasswordEncoder()
        val user = User(
            name=userDto.name,
            password=encoder.encode(userDto.password)
        )
        val createdUser: User = repository.save(user)
        return createdUser.id!!
    }

    override fun loadUserByUsername(name: String): User {
        return repository.findByName(name) ?: throw UsernameNotFoundException("Can not found username:$name)")
    }
//    override fun loadUserByUsername(name: String): User =
//        repository.findByName(name)!!
}