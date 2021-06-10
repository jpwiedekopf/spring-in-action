package com.example.springinaction.tacos

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/register")
@Profile("!initdb")
class RegistrationController(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder
) {

    private val log: Logger = LoggerFactory.getLogger(RegistrationController::class.java)

    @GetMapping
    fun registerForm() = "registrationForm"

    @PostMapping
    fun processRegistration(form: RegistrationForm): String {
        log.info("Registering new user ${form.username}")
        userRepository.save(form.toUser(passwordEncoder))
        return "redirect:/login"
    }
}