package com.example.springinaction.tacos

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
data class Taco(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    @get:NotEmpty
    @get:Size(min = 5, message = "Name must be at least 5 characters long")
    val name: String? = null,

    @ManyToMany(targetEntity = Ingredient::class)
    @get:NotEmpty
    @get:Size(min = 1, message = "You must choose at least 1 ingredient")
    val ingredients: List<Ingredient>? = null,

    var createdAt: Date = Date()
) {

    @PrePersist
    fun createdAt() {
        this.createdAt = Date()

    }
}
