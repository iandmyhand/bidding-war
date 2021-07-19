package com.example.bidding

import org.springframework.data.repository.CrudRepository

interface BiddingRepository: CrudRepository<Product, Long>
