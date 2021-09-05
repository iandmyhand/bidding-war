package com.example.biddingwar.controller

import com.example.biddingwar.dto.UserLoginForm
import com.example.biddingwar.dto.UserSignInForm
import com.example.biddingwar.entity.User
import com.example.biddingwar.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user")
class UserController(@Autowired val userRepository: UserRepository) {

    @GetMapping("/signin")
    fun signin_get(): String {
        return "Users/signInForm"
    }

    @PostMapping("/signin")
    fun signin_post(userForm : UserSignInForm): String{
        userRepository.save(userForm.toEntity())

        return "redirect:/user/login"
    }

    @GetMapping("/login")
    fun login_get(session: HttpSession, model: Model): String {
        val userId = session.getAttribute("userId")

        return "Users/loginForm"
    }

    @PostMapping("/login")
    fun login_post(userForm : UserLoginForm, session: HttpSession, model:Model): String{
        val (userId, userPw) = userForm.toEntity()
        val user = userRepository.findUserByUserIdAndUserPw(userId, userPw)
        var response: String = ""

        if (user != null){
            session.setAttribute("userId", user.userId)

            model["userName"] = user.userName
            model["content"] = "로그인을 환영합니다."

            response = "greetings"
        }else{
            response = "redirect:/user/login?error=1"
        }

        return response
    }

    @GetMapping("/logout")
    fun logout_post(session: HttpSession, model:Model): String{
        val userId = session.getAttribute("userId")

        if(userId != null){
            session.removeAttribute("userId")
            session.invalidate()

            print("userID is not None ${userId}")
        }

        return "redirect:/welcome/bye"
    }
}