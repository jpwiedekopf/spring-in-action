package com.example.springinaction.tacos

import com.example.springinaction.tacos.Ingredient.Type
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/design")
class DesignController {

    private val log = LoggerFactory.getLogger(DesignController::class.java)

    @PostMapping
    fun processDesign(@Valid taco: Taco, errors: Errors, model: Model) =
        when (errors.hasErrors()) {
            true -> {
                log.warn("Validation errors for design: ${errors.errorCount}")
                setupIngredientModel(model)
                "designForm"
            }

            else -> {
                log.info("Processing design: $taco")
                "redirect:/orders/current"
            }
        }

    private fun setupIngredientModel(model: Model) {
        val ingredients = listOf(
            Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Type.PROTEIN),
            Ingredient("TMTO", "Tomato", Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            Ingredient("SLSA", "Salsa", Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        )
        val types = Type.values()
        types.forEach { itype ->
            model[itype.toString().lowercase()] = ingredients.filter { i ->
                i.type == itype
            }
        }
    }

    @GetMapping
    fun designForm(model: Model): String {
        /*for (t in listOf("wrap", "protein", "veggies", "cheese", "sauce")) {
            model[t] = listOf<Ingredient>()
        }*/
/*        model["wrap"] = listOf<Ingredient>()
        model["protein"] = listOf<Ingredient>()
        model["veggies"] = listOf<Ingredient>()
        model["cheese"] = listOf<Ingredient>()
        model["sauce"] = listOf<Ingredient>()*/
        model["taco"] = Taco()
        setupIngredientModel(model)
        return "designForm"
    }
}