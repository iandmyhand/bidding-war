package com.example.biddingwar.controller

import com.example.biddingwar.dto.UserSignInForm
import com.example.biddingwar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/user")
class UserController(@Autowired val userRepository: UserRepository) {

    @GetMapping("/signin")
    fun sign_in_get(): String {
        return "Users/signInForm"
    }

    @PostMapping("/signin")
    fun sign_in_post(userForm : UserSignInForm): String{
        userRepository.save(userForm.toEntity())

        return "redirect:items/list"

    }




}