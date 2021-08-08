package com.example.biddingwar

import org.springframework.stereotype.Service
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
            if (signedUpUser.user_name == user.user_name) {
                return false
            }
        }

        repository.save(user)
        return true
    }

    fun getAll() = repository.findAll()
}
