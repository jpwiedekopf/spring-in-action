package com.example.springinaction.tacos

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class Taco(
    @get:NotEmpty
    @get:Size(min = 5, message = "Name must be at least 5 characters long")
    val name: String? = null,
    @get:NotEmpty
    @get:Size(min = 1, message = "You must choose at least 1 ingredient")
    val ingredients: List<String>? = null
)
