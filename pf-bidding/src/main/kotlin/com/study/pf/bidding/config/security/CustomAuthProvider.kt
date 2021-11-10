package com.study.pf.bidding.config.security

import com.study.pf.common.common.util.IpNoUtil
import com.study.pf.bidding.service.SignInService
import com.study.pf.bidding.domain.dto.SessionDto
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import java.lang.String
import kotlin.Boolean
import kotlin.Long

@Component
class CustomAuthProvider(private val signInService: SignInService): AuthenticationProvider {

    override fun authenticate(authentication: Authentication?): Authentication {
        var webAuth = authentication!!.details as WebAuthenticationDetails

        val remoteIp = webAuth.remoteAddress
        val remoteIpNo: Long = IpNoUtil.toIpNo(remoteIp)

        /*var locale = LocaleContextHolder.getLocale()
        val supportCode = "/KO/EN/"
        val localeCode = locale.language.uppercase(Locale.getDefault())

        if (supportCode.indexOf(localeCode) < 0) {
            locale = Locale.ENGLISH
        }*/

        // 아이디 및 패스워드 validation @todo 작업으로..~
        val authId = String.valueOf(authentication.principal)
        var authPw = String.valueOf(authentication.credentials)
//      authPw = PasswordBCrypto.hashPassword(authPw)

        var user = signInService.signinUser(authId, authPw) // 로그인

        val sessionDto = SessionDto(
                sessionId = "default:" + authId,
                remoteIpNo = remoteIpNo,
                userId = user!!.userId,
                userName = user.userName,
                userNick = user.userNick
        )

        // 권한 부여 --> 없을 시 GUEST 권한 발행
        val roles: MutableCollection<SimpleGrantedAuthority> = ArrayList()

        if (roles.isEmpty()) {
            roles.add(SimpleGrantedAuthority("ROLE_GUEST"))
        }

        // 토큰 발행 --> @TODO : 리펙토링 필요
        val authenticationToken = UsernamePasswordAuthenticationToken(authId, authPw, roles)
        authenticationToken.details = sessionDto

        return authenticationToken
    }

    override fun supports(authentication: Class<*>?): Boolean {
        // (UsernamePasswordAuthenticationToken)
        return true
    }
}