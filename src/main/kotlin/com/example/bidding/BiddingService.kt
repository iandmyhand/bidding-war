package com.example.bidding

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class BiddingWarService(val repository: BiddingRepository) {
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
