package com.study.biddingwar.service

import com.study.biddingwar.domain.dto.SignUpDto
import com.study.biddingwar.domain.dto.SignUpResultDto
import org.springframework.stereotype.Service

@Service
class SingUpService(private val userService: UserService) {
    fun signUpUser(signUpDto: SignUpDto): SignUpResultDto {
        val user = userService.createUser(signUpDto.userName, signUpDto.userPassword)
        return SignUpResultDto(user.id!!, user.userName, user.createdAt)
    }
}