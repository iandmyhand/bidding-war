import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @get:Bean
    val passwordEncoder: PasswordEncoder
        get() = BCryptPasswordEncoder()

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
    }
}