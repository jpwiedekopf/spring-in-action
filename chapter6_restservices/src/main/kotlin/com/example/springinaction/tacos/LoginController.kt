package com.example.springinaction.tacos

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
@Profile("!initdb")
class LoginController {

    @GetMapping("/login")
    fun login() = "login"

    @GetMapping("/login-error")
    fun loginError(model: Model): String {
        model["error"] = true
        return "login"
    }
}