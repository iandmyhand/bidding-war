package com.study.peoplefund.web

import com.study.peoplefund.service.UserService
import com.study.peoplefund.web.dto.SignInRequest
import com.study.peoplefund.web.dto.SignInResponse
import com.study.peoplefund.web.dto.UserRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/api/users")
class UserController(val userService: UserService) {

    @PostMapping
    fun signUp(@RequestBody request: UserRequest): ResponseEntity<Void> {
        val id = userService.signUp(request)
        return ResponseEntity.created(URI.create("/api/users/$id")).build()
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<SignInResponse> {
        val signInResponse = userService.signIn(request)

        return ResponseEntity.ok(signInResponse)
    }
}