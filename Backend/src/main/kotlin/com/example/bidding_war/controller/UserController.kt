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
class UserController(val userService: UserService, val sessionRepository: SessionRepository){

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
            val session = Session(
                key = UUID.randomUUID().toString(),
                email = user.email
            )

            sessionRepository.save(session)
            ResponseEntity.ok(
                "register complete $user")

        } else{
            ResponseEntity.status(404).body("존재하지 않는 계정입니다.")
        }

    }
}
