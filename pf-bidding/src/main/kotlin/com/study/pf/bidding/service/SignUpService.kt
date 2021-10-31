package com.study.pf.bidding.service

import com.study.pf.bidding.domain.dto.SignUpDto
import com.study.pf.bidding.domain.dto.SignUpResultDto
import org.springframework.stereotype.Service

@Service
class SignUpService(private val userService: UserService) {

    fun signUpUser(signUpDto: SignUpDto): SignUpResultDto {
        val user = userService.createUser(signUpDto)
        return SignUpResultDto(user.userId!!, user.userName, user.userNick, user.createDt)
    }
}