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
        val result = service.signUP(user)

        if (!result) {
            return ResponseEntity.status(400).body("중복 ID 입니다.")
        }

        return ResponseEntity.ok(
            "회원가입 성공."
        )
    }

    @PostMapping("/login")
    fun signIn(@RequestBody user: User, request: HttpServletRequest): ResponseEntity<String> {

        val user = service.signIn(user, request)

        return if (user != null) {
            ResponseEntity.ok("${user.id}")
        } else {
            ResponseEntity.status(401).body("로그인 실패")
        }

    }
}