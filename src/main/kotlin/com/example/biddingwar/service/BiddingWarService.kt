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

    val bcrypt = BCryptPasswordEncoder(11)

    fun signUP(user: User): Boolean {
        val signedUpUsers: MutableIterable<User> = getAll()

        for(signedUpUser in signedUpUsers) {
            if (signedUpUser.email == user.email) {
                return false
            }
        }


        user.pwd = bcrypt.encode(user.password)
        repository.save(user)

        return true
    }

    fun signIn(userRequest: User): User? {
        // 이메일을 통한 유저 조회
        val user = repository.findByEmail(userRequest.email)

        if (user != null) {
            // 비밀번호 검증
            if (bcrypt.matches(userRequest.pwd, user?.password)) {
                user.session = Pair(user.id, UUID.randomUUID().toString())

                return user
            }
        }

        return null
    }

    fun getAll() = repository.findAll()

}
