package kr.co.peoplefund.biddingWar.service

import kr.co.peoplefund.biddingWar.controller.dto.UserRequest
import kr.co.peoplefund.biddingWar.domain.repository.UserRepository
import kr.co.peoplefund.biddingWar.utils.PasswordUtils
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun register(request: UserRequest): Long {
        val user = request.toUser()
        user.password = PasswordUtils.hash(user.password, PasswordUtils.generateSalt()).toString()
        println(user.userId)
        println(user.password)
        return userRepository.save(user).id!!
    }

}