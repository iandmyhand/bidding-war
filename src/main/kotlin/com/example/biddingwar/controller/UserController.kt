package com.example.biddingwar.controller

import com.example.biddingwar.dto.UserLoginForm
import com.example.biddingwar.dto.UserSignInForm
import com.example.biddingwar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpSession

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

        return "redirect:/user/login"
    }


    @GetMapping("/login")
    fun login_get(): String {
        return "Users/loginForm"
    }

    @PostMapping("/login")
    fun login_post(userForm : UserLoginForm, session: HttpSession, model:Model): String{
        val (userId, userPw) = userForm.toEntity()
//        val user = userRepository.findUserByUserIdEqualsAndUserPwEquals(userId, userPw)
        val user = userRepository.findUserByUserIdAndUserPw(userId, userPw)
//        val user = userRepository.findUserByUserIdEquals(userId)
        var response: String = ""

        if (user != null){
            session.setAttribute("userId", user.userId)

            model["userName"] = user.userName
            model["content"] = "로그인을 환영합니다."

            response = "greetings"
        }else{
            model["userName"] = "???"
            model["content"] = "로그인에 실패했습니다."

            response = "greetings"
//            response = "redirect:/user/login"
        }

        return response

    }

    @PostMapping("/logout")
    fun logout_post(): String{
        return "redirect:/"
    }
}