package com.example.springinaction.tacos

import com.example.springinaction.tacos.Ingredient.IngredientType
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
@Profile("!initdb")
class DesignTacoController(
    @Autowired private val ingredientRepository: IngredientRepository,
    @Autowired private val tacoRepository: TacoRepository
) {

    private val log = LoggerFactory.getLogger(DesignTacoController::class.java)

    @ModelAttribute(name = "order")
    fun order() = Order()

    @ModelAttribute(name = "design")
    fun design(): Taco {
        return Taco()
    }

    @PostMapping
    fun processDesign(
        @Valid taco: Taco,
        errors: Errors,
        @ModelAttribute order: Order,
        model: Model
    ) = when (errors.hasErrors()) {
            true -> {
                log.debug("$errors")
                log.warn("Validation errors for design: ${errors.errorCount}")
                setupIngredientModel(model)
                "designForm"
            }

            else -> {
                log.info("Added design: $taco")
                tacoRepository.save(taco)
                order.tacos.add(taco)
                // sessionStatus.setComplete()
                "redirect:/orders/current"
            }
        }

    private fun setupIngredientModel(model: Model) {
        val ingredients = ingredientRepository.findAll().toList()
        IngredientType.values().forEach { i_type ->
            model[i_type.toString().lowercase()] = ingredients.filter { i ->
                i.type == i_type
            }
        }
    }

    @GetMapping
    fun designForm(
        model: Model
    ): String {
        //model["design"] = taco()
        setupIngredientModel(model)
        return "designForm"
    }
}