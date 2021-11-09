package com.example.biddingwar.controller

import com.example.biddingwar.database.User
import com.example.biddingwar.service.user.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("")
class UserController(val service: UserService) {
    @GetMapping("/users")
    fun users() = ResponseEntity.ok(service.getAll())

    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: User) : ResponseEntity<String> {
        return service.signUp(user)
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody user: User, request: HttpServletRequest): ResponseEntity<String> {
        return service.signIn(user, request)
    }
}