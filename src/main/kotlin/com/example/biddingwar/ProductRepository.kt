package com.example.biddingwar

import org.springframework.data.repository.CrudRepository

interface ProductRepository : CrudRepository<Product, Long>