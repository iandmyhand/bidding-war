package com.study.biddingwar.controller

import com.study.biddingwar.service.SignInService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class SignInController(private val signInService: SignInService) {


}