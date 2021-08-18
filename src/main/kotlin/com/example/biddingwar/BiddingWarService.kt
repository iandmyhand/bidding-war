package com.example.biddingwar

import org.springframework.stereotype.Service
import java.security.MessageDigest
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
    fun signUP(user: User): Boolean {
        val signedUpUsers: MutableIterable<User> = getAll()

        for(signedUpUser in signedUpUsers) {
            if (signedUpUser.email == user.email) {
                return false
            }
        }

        user.password = encodePassword(user.password)

        repository.save(user)
        return true
    }

    fun signIn(user: User, session: HttpSession): Boolean {
        val dataUser: User? = repository.findByEmail(user.email)
        val loginPassword: String = encodePassword(user.password)

        if (dataUser != null) {
            if (loginPassword.equals(dataUser.password) {
                session.setAttribute("user_id", dataUser.id)
            }
        }

        return false

    }

    fun getAll() = repository.findAll()

    fun encodePassword(password: String): String{
        val sha = MessageDigest.getInstance("SHA-256")
        val hexa = sha.digest(password.toByteArray())
        return hexa.fold("", {str, it -> str + "%02x".format(it)})
    }
}
