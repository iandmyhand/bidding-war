package com.peoplefund.biddingwar.product

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService(@Autowired val productRepository: ProductRepository) {
    fun list(searchCondition: ProductSearch): List<Product> {

        if (searchCondition.name != null) {
            return productRepository.findByNameContains(searchCondition.name)
        }

        return productRepository.findAll().toList()
    }
}


@Service
class UserService(@Autowired val userRepository: UserRepository) {
    fun signUp(userRequestBody: UserSignupRequest): User {
        if (userRepository.existsUserByUserId(userRequestBody.userId)) {
            throw AlreadyExistUserException()
        }

        return userRepository.save(userRequestBody.entityOf())
    }
}