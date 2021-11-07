package com.study.biddingwar.controller

import com.study.biddingwar.domain.dto.SignUpDto
import com.study.biddingwar.domain.dto.SignUpResultDto
import com.study.biddingwar.service.SingUpService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class SignUpController(private val signUpService: SingUpService) {

    @PostMapping("/user/signup")
    fun signUpUser(@RequestBody signUpDto: SignUpDto): ResponseEntity<SignUpResultDto> {
        val signUpResult = signUpService.signUpUser(signUpDto)
        return ResponseEntity.ok().body(signUpResult)
    }
}