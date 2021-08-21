package com.example.bidding_war.web.controller

import com.example.bidding_war.service.UserService
import com.example.bidding_war.web.dto.User.SignInRequest
import com.example.bidding_war.web.dto.User.SignInResponse
import com.example.bidding_war.web.dto.User.UserRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI



@RestController
@RequestMapping("/api/users")
class UserController(val userService: UserService){

    @PostMapping
    fun signUp(@RequestBody request: UserRequest): ResponseEntity<Void> {
        val id = userService.signUp(request)
        return ResponseEntity.created(URI.create("/api/users/$id")).build()
    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<SignInResponse> {
        val signInResponse = userService.signIn(request)

        return ResponseEntity.ok(signInResponse)
    }
}
