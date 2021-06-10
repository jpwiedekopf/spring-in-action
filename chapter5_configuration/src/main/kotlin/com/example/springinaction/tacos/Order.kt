package com.example.springinaction.tacos

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "Taco_Order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    var placedAt: Date = Date(),

    @field:NotBlank(message = "Name is required")
    var deliveryName: String? = null,

    @field:NotBlank(message = "Street is required")
    var deliveryStreet: String? = null,

    @field:NotBlank(message = "City is required")
    var deliveryCity: String? = null,

    @field:NotBlank(message = "State is required")
    var deliveryState: String? = null,

    @field:NotBlank(message = "ZIP code is required")
    var deliveryZip: String? = null,

    //@field:CreditCardNumber(message = "Not a valid credit card number")
    @field:NotBlank(message = "Credit card number is required")
    var ccNumber: String? = null,

    //@field:Pattern(regexp = "^(0[1-9]|1[0-2])([/])([1-9][0-9])$", message = "Must be in format MM/YY")
    @field:NotBlank(message = "Credit card expiry is required")
    var ccExpiration: String? = null,

    @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    @field:NotBlank(message = "Credit card CVV is required")
    var ccCvv: String? = null,

    @ManyToMany(targetEntity = Taco::class)
    val tacos: MutableList<Taco> = mutableListOf(),

    @ManyToOne
    var user: User? = null
) {

    @Suppress("unused")
    @get:Transient
    val numberOfTacos
        get() = tacos.size

    @PrePersist
    fun placedAt() {
        this.placedAt = Date()
    }
}
