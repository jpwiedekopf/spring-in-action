package com.example.springinaction.tacos

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.util.matcher.RequestMatcher

@Configuration
@EnableWebSecurity
@Profile("!initdb")
class SecurityConfig(
    @Autowired val userRepositoryDetailsService: UserRepositoryDetailsService,
    @Autowired val passwordEncoder: PasswordEncoder
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        super.configure(auth)
        auth.userDetailsService(userRepositoryDetailsService)
            .passwordEncoder(passwordEncoder)
    }

    override fun configure(http: HttpSecurity?) {
        if (http == null) return
        http.invoke {
            httpBasic { }
            authorizeRequests {
                authorize("/design", hasRole("ROLE_USER"))
                authorize("/orders", hasRole("ROLE_USER"))
                authorize("/**", permitAll)
            }
            formLogin {
                loginPage = "/login"
                loginProcessingUrl = "/authenticate"
                failureUrl = "/login-error"
            }
            logout {}
        }
    }

//    override fun configure(http: HttpSecurity?) {
//        http?.invoke {
//            httpBasic { }
//            authorizeRequests {
//                authorize("/design/**", hasAuthority("ROLE_USER"))
//                authorize("/order/**", hasAuthority("ROLE_USER"))
//                authorize("/**", permitAll)
//            }
//        }
//    }
}

