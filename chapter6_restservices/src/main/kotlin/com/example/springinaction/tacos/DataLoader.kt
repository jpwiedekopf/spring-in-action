package com.example.springinaction.tacos

import com.example.springinaction.tacos.Ingredient.IngredientType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@Profile(value = ["initdb"])
class DataLoader {
    private val log: Logger = LoggerFactory.getLogger(DataLoader::class.java)

    @Bean
    fun dataloader(
        ingredientRepository: IngredientRepository,
        userRepository: UserRepository,
        passwordEncoder: PasswordEncoder
    ) = CommandLineRunner {
        ingredientRepository.deleteAll()
        ingredientRepository.saveAll(
            listOf(
                Ingredient(null, "FLTO", "Flour Tortilla", IngredientType.WRAP),
                Ingredient(null, "COTO", "Corn Tortilla", IngredientType.WRAP),
                Ingredient(null, "GRBF", "Ground Beef", IngredientType.PROTEIN),
                Ingredient(null, "CARN", "Carnitas", IngredientType.PROTEIN),
                Ingredient(null, "VGCK", "Vegan Chicken", IngredientType.PROTEIN),
                Ingredient(null, "TMTO", "Tomato", IngredientType.VEGGIES),
                Ingredient(null, "LETC", "Lettuce", IngredientType.VEGGIES),
                Ingredient(null, "CHED", "Cheddar", IngredientType.CHEESE),
                Ingredient(null, "JACK", "Monterrey Jack", IngredientType.CHEESE),
                Ingredient(null, "MOZA", "Mozzarella", IngredientType.CHEESE),
                Ingredient(null, "SLSA", "Salsa", IngredientType.SAUCE),
                Ingredient(null, "SRCR", "Sour Cream", IngredientType.SAUCE)
            )
        )
        log.warn("Created ingredients")
        userRepository.save(
            User(
                id = null,
                username = "a",
                password = passwordEncoder.encode("a"),
                fullName = "Joshua Wiedekopf",
                street = "Ratzeburger Allee 160",
                city = "Lübeck",
                state = "SH",
                zip = "23562",
                phoneNumber = "+49 451 3101-0"
            )
        )
        log.warn("Created users")
    }

    /*@Bean
    @Profile("h2")
    fun h2dataloader(
        ingredientRepository: IngredientRepository,
        userRepository: UserRepository,
        passwordEncoder: PasswordEncoder
    ) = dataloader(ingredientRepository, userRepository, passwordEncoder)*/
}