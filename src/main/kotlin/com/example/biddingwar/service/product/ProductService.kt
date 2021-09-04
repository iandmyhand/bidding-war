package com.example.biddingwar.service.product

import com.example.biddingwar.database.Product
import com.example.biddingwar.repository.ProductRepository
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

@Service
@Transactional
class ProductService(val repository: ProductRepository) {

    fun getAll() = repository.findAll()

    fun get(id: Long) = repository.findById(id)

    fun save(product: Product, request: HttpServletRequest): Boolean {
        val session = request.session
        if (session.getAttribute("session") == null) {
            return false
        }

        repository.save(product)
        return true
    }

    fun delete(id: Long): Boolean {
        val productToDelete = repository.existsById(id)

        if (productToDelete) {
            repository.deleteById(id)
        }

        return productToDelete
    }

}
