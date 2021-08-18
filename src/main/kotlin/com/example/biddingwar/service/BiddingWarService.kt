package com.example.biddingwar

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*
import javax.servlet.http.HttpSession
import javax.transaction.Transactional

@Service
@Transactional
class BiddingWarService(val repository: BiddingWarRepository) {
    fun getAll() = repository.findAll()

    fun get(id: Long) = repository.findById(id)

    fun save(product: Product) = repository.save(product)

    fun delete(id: Long): Boolean {
        val productToDelete = repository.existsById(id)

        if (productToDelete) {
            repository.deleteById(id)
        }

        return productToDelete
    }
}

@Service
@Transactional
class BiddingWarUserService(val repository: BiddingWarUserRepository) {
    fun signUP(user: User): Boolean {
        val signedUpUsers: MutableIterable<User> = getAll()

        for(signedUpUser in signedUpUsers) {
            if (signedUpUser.email == user.email) {
                return false
            }
        }

        val bcrypt = BCryptPasswordEncoder(11)
        user.pwd = bcrypt.encode(user.password)
        repository.save(user)

        return true
    }

    fun signIn(userRequest: User): User? {
        val user = repository.findByName(userRequest.name)

        if (user != null) {
            user.session = Pair(user.id, UUID.randomUUID().toString())
                return user
        }

        return null

    }

    fun getAll() = repository.findAll()

}
