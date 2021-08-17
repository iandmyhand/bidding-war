package com.example.bidding_war.controller

import com.example.bidding_war.model.User
import com.example.bidding_war.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/users")
class UserController(val userService: UserService){

    @GetMapping
    fun get_all() = ResponseEntity.ok(userService.findAll())

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ResponseEntity.ok(userService.findById(id))

    @PostMapping("/signUp")
    fun signUp(@RequestBody user: User): ResponseEntity<String> {
        val result = userService.signUp(user)

        return if (!result) {
            ResponseEntity.status(404).body("이미 존재하는 계정입니다.")
        } else{
            userService.register(user)
            return ResponseEntity.ok(
                "register complete $user"
            )
        }

    }

    @PostMapping("/signIn")
    fun signIn(@RequestBody user: User): ResponseEntity<String> {
        val result = userService.signIn(user)

        return if (result) {
            ResponseEntity.ok(
                "register complete $user")
        } else{
            ResponseEntity.status(404).body("존재하지 않는 계정입니다.")
        }

    }
}
