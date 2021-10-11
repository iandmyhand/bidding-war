package com.peoplefund.biddingwar.users

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun existsUserByUserId(userId: String): Boolean

    fun findByUserId(userId: String): Optional<User>

}