package com.example.springinaction.tacos

import javax.persistence.*

@Entity
data class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val dbId: Long,
    val id: String,
    val name: String,
    @Enumerated(EnumType.STRING)
    val type: IngredientType
) {
    enum class IngredientType {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE
    }
}
