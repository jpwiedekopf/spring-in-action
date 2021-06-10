package com.example.springinaction.tacos

import com.example.springinaction.tacos.Ingredient.Type
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/design")
class DesignTacoController {

    @GetMapping
    fun showDesignForm(model: Model) : String {
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
        types.forEach { model.addAttribute(it.toString().lowercase(), filterByType(ingredients, it)) }
        model.addAttribute("design", Taco())
        return "design"
    }

    private fun filterByType(ingredients: List<Ingredient>, type: Type): List<Ingredient> =
        ingredients.filter { it.type == type }
}