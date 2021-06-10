package com.example.springinaction.tacos

import com.example.springinaction.tacos.Ingredient.IngredientType
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
class DesignController(
    @Autowired private val ingredientRepository: IngredientRepository,
    @Autowired private val tacoRepository: TacoRepository
) {

    private val log = LoggerFactory.getLogger(DesignController::class.java)

    @PostMapping
    fun processDesign(
        @Valid @ModelAttribute("taco") taco: Taco,
        errors: Errors,
        model: Model,
        sessionStatus: SessionStatus
    ) =
        when (errors.hasErrors()) {
            true -> {
                log.debug("$errors")
                log.warn("Validation errors for design: ${errors.errorCount}")
                setupIngredientModel(model)
                "designForm"
            }

            else -> {
                log.info("Processing design: $taco")
                tacoRepository.save(taco)
                sessionStatus.setComplete()
                "redirect:/orders/current"
            }
        }

    private fun setupIngredientModel(model: Model) {
        val ingredients = ingredientRepository.findAll().toList()
        IngredientType.values().forEach { itype ->
            model[itype.toString().lowercase()] = ingredients.filter { i ->
                i.type == itype
            }
        }
    }

    @GetMapping
    fun designForm(model: Model): String {
        model["taco"] = Taco()
        setupIngredientModel(model)
        return "designForm"
    }
}