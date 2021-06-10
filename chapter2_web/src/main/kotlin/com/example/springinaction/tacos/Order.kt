package com.example.springinaction.tacos

import org.hibernate.validator.constraints.CreditCardNumber
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class Order(
    @field:NotBlank(message = "Name is required")
    val name: String? = null,
    @field:NotBlank(message = "Street is required")
    val street: String? = null,
    @field:NotBlank(message = "City is required")
    val city: String? = null,
    val state: String? = null,
    @field:NotBlank(message = "ZIP code is required")
    val zip: String? = null,
    @field:CreditCardNumber(message = "Not a valid credit card number")
    val ccNumber: String? = null,
    @field:Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be in format MM/YY")
    val ccExpiration: String? = null,
    @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    val ccCVV: String? = null
)
