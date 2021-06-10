package com.example.springinaction.tacos

import org.hibernate.validator.constraints.CreditCardNumber
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
@Table(name = "Taco_Order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    var placedAt: Date = Date(),

    @field:NotBlank(message = "Name is required")
    val deliveryName: String? = null,

    @field:NotBlank(message = "Street is required")
    val deliveryStreet: String? = null,

    @field:NotBlank(message = "City is required")
    val deliveryCity: String? = null,

    @field:NotBlank(message = "State is required")
    val deliveryState: String? = null,

    @field:NotBlank(message = "ZIP code is required")
    val deliveryZip: String? = null,

    @field:CreditCardNumber(message = "Not a valid credit card number")
    val ccNumber: String? = null,

    @field:Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be in format MM/YY")
    val ccExpiration: String? = null,

    @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    val ccCvv: String? = null,

    @ManyToMany(targetEntity = Taco::class)
    private val tacos: MutableList<Taco>? = null
) {

    @PrePersist
    fun placedAt() {
        this.placedAt = Date()
    }

    fun addDesign(design: Taco) = tacos?.add(design)

}
