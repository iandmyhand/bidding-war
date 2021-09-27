package com.study.biddingwar.service

import com.study.biddingwar.domain.dto.SignUpDto
import com.study.biddingwar.domain.dto.SignUpResultDto
import org.springframework.stereotype.Service

@Service
class SignUpService(private val userService: UserService) {

    fun signUpUser(signUpDto: SignUpDto):SignUpResultDto {
        val user = userService.createUser(signUpDto)
        return SignUpResultDto(user.userId!!, user.userName, user.userNick, user.createDt)
    }
}