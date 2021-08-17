package com.study.peoplefund.service

import com.study.peoplefund.domain.repository.SessionRepository
import com.study.peoplefund.domain.repository.UserRepository
import com.study.peoplefund.web.dto.UserRequest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserServiceTest @Autowired constructor(
        val userRepository: UserRepository,
        val sessionRepository: SessionRepository
) {
    val userService = UserService(userRepository, sessionRepository)

    @Test
    fun `회원가입`() {
        val userRequest = UserRequest(
                account = "kuenhwi",
                password = "abcd1234",
                name = "최근휘"
        )
        val savedId = userService.signUp(userRequest)

        val user = userRepository.findById(savedId).orElseThrow()

        assertAll(
                {
                    assertThat(user.id).isNotNull
                },
                {
                    assertThat(user)
                            .extracting("account", "password", "name")
                            .isEqualTo(listOf("kuenhwi", "abcd1234", "최근휘"))
                }
        )
    }

    @Test
    fun `중복 회원가입은 실패해야한다`() {
        val userRequest = UserRequest(
                account = "kuenhwi",
                password = "abcd1234",
                name = "최근휘"
        )
        userService.signUp(userRequest)

        assertThatThrownBy {
            userService.signUp(userRequest)
            userRepository.flush()
        }
    }

    @Test
    fun `로그인 테스트`() {
        // TODO
    }
}