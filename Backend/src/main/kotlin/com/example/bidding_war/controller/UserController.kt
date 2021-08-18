package com.example.bidding_war.controller

import com.example.bidding_war.model.Session
import com.example.bidding_war.model.User
import com.example.bidding_war.repository.SessionRepository
import com.example.bidding_war.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/users")
class UserController(val userService: UserService){

    @GetMapping
    fun get_all() = ResponseEntity.ok(userService.findAll())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ResponseEntity.ok(userService.findById(id))

    @PostMapping("/signUp")
    fun signUp(@RequestBody user: User) = ResponseEntity.ok(userService.signUp(user))

    @PostMapping("/signIn")
    fun signIn(@RequestBody user: User) = ResponseEntity.ok(userService.signIn(user))
}
