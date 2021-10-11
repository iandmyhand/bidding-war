package com.study.peoplefund.helper

import com.study.peoplefund.domain.repository.SessionRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.security.PasswordHasher
import com.study.peoplefund.service.AuthService
import com.study.peoplefund.web.arguments.user.AuthInfo
import com.study.peoplefund.web.dto.SignInRequest
import com.study.peoplefund.web.dto.UserRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.security.SecureRandom

@DataJpaTest
class AuthHelper @Autowired constructor(
    val userRepository: UserRepository,
    val sessionRepository: SessionRepository
) {
    val authService = AuthService(userRepository, sessionRepository, PasswordHasher(SecureRandom()))

    fun asSignUp(
        account: String = "kuenhwi@google.com",
        password: String = "abcd1234",
        name: String = "kuenhwi"
    ): Long {
        val signUpRequest = UserRequest(
            account = "kuenhwi@google.com",
            password = "abcd1234",
            name = "kuenhwi"
        )

        return authService.signUp(signUpRequest)
    }

    fun asSignIn(
        account: String = "kuenhwi@google.com",
        password: String = "abcd1234",
    ): AuthInfo {
        val signInRequest = SignInRequest(
            account = "kuenhwi@google.com",
            password = "abcd1234"
        )

        val signInResponse = authService.signIn(signInRequest)

        val session = sessionRepository.findByToken(signInResponse.token).get()

        return session.toAuthInfo()
    }
}