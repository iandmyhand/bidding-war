
package com.study.peoplefund.web.dto

import com.study.peoplefund.domain.User

data class UserRequest(
    val account: String,
    val password: String,
    val name: String
) {
    fun toUser(): User {
        return User(
            account = this.account,
            password = this.password,
            name = this.name
        )
    }
}
