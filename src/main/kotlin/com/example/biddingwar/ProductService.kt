package com.example.biddingwar

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductService(val repository: ProductRepository){

    fun getAll() = repository.findAll()

    fun getProduct(id: Long) = repository.findById(id)

    fun save(product: Product) = repository.save(product)
}