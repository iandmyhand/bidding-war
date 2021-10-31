package com.study.pf.bidding.controller

import com.study.pf.bidding.service.SignUpService
import com.study.pf.bidding.domain.dto.SignUpDto
import com.study.pf.bidding.domain.dto.SignUpResultDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 회원 가입용 Controller
 */
@RestController
@RequestMapping("/v1")
class SignUpController(private val signUpService: SignUpService) {

    /**
     * view 단에서 중복아이디 조회 방어로직이 없음
     * @TODO 해당 로직에 추가하기보단 별도 프로세스로 관리되는것이 맞다고 생각됨 // 중복가입 안되도록 추가 구현할 것!
     */
    @PostMapping("/user/signup")
    fun signUpUser(@RequestBody signUpDto: SignUpDto):ResponseEntity<SignUpResultDto> {
        val signUpResult = signUpService.signUpUser(signUpDto)
        return ResponseEntity.ok().body(signUpResult)
    }
}