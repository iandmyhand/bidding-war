package com.study.peoplefund.domain

import com.study.peoplefund.domain.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserTest @Autowired constructor(
        val userRepository: UserRepository
) {

    @Test
    fun `로그인 쿼리 테스트`() {
        val user = User(
                account = "kuenhwi",
                password = "abcd1234",
                name = "choi",
                salt = "abcd".toByteArray()
        )

        userRepository.save(user)

        val saved = userRepository
                .findByAccountAndPassword("kuenhwi", "abcd1234")
                .get()

        assertThat(saved)
                .extracting(User::account, User::password, User::name)
                .isEqualTo(listOf("kuenhwi", "abcd1234", "choi"))
    }

    @ParameterizedTest
    @CsvSource("kuenhwi,wrong", "wrong,abcd1234")
    fun `실패하는 경우 로그인 쿼리 테스트`(account: String, password: String) {
        val user = User(
                account = "kuenhwi",
                password = "abcd1234",
                name = "choi",
                salt = "abcd".toByteArray()
        )

        userRepository.save(user)

        assertThatThrownBy {
            userRepository.findByAccountAndPassword(account, password).orElseThrow {
                throw IllegalArgumentException()
            }
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
    }
}