package com.study.biddingwar.controller

import com.study.biddingwar.service.SignInService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1")
class SignInController(private val signInService: SignInService) {

    @PostMapping("/user/signout")
    fun signOut(userId: String): ResponseEntity<String> {
        signInService.signoutUser(userId)
        return ResponseEntity.ok().body("logout ok")
    }
}