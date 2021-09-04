package com.example.biddingwar

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional


@Service
@Transactional
class BiddingWarService(val repository: BiddingWarRepository) {
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

    fun signIn(userRequest: User, request: HttpServletRequest): User? {
        // 이메일을 통한 유저 조회
        val user = repository.findByEmail(userRequest.email)

        if (user != null) {
            // 비밀번호 검증
            if (bcrypt.matches(userRequest.pwd, user?.password)) {
                val session = request.session
                session.setAttribute("session", user.id)

                return user
            }
        }

        return null
    }

    fun getAll() = repository.findAll()

}

@Service
@Transactional
class BiddingWarBidService(val bidRepository: BiddingWarBidRepository, val productRepository: BiddingWarRepository) {
    fun getAll() = bidRepository.findAll()

    fun getUserBids(userId: Long): List<Bid>? {
        return bidRepository.findByUserId(userId)
    }

    fun saveBid(bid: Bid, request: HttpServletRequest): Boolean {
        val product = productRepository.findById(bid.productId).get()
        bidRepository.save(bid)
        return true
    }
}
