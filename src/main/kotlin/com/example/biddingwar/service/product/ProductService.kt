package com.example.biddingwar.service.product

import com.example.biddingwar.database.Product
import com.example.biddingwar.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional

@Service
@Transactional
class ProductService(val repository: ProductRepository) {

    fun getAll(): ResponseEntity<MutableIterable<Product>> = ResponseEntity.ok().body(repository.findAll())

    fun get(id: Long) = repository.findById(id)

    fun save(product: Product, request: HttpServletRequest): ResponseEntity<Product> {
        val session = request.session
        if (session.getAttribute("session") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        repository.save(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    fun delete(id: Long): ResponseEntity<String> {
        val productToDelete = repository.existsById(id)

        if (productToDelete) {
            repository.deleteById(id)
            return ResponseEntity.ok().body("DELETED")
        }

        return ResponseEntity.badRequest().body("DELETED_FAILED")
    }

}
