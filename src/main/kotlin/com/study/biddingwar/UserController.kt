package com.study.biddingwar

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(val service: UserService) {
    @PostMapping
    fun signup(@RequestBody userDto: UserDto): String {
        service.save(userDto)
        return "redirect:/login"
    }
}