package com.example.springinaction.tacos

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
class OrderController {

    private val log = LoggerFactory.getLogger(OrderController::class.java)

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        model["order"] = Order()
        return "orderForm"
    }

    @PostMapping
    fun processOrder(@Valid order: Order, errors: Errors) =
        when (errors.hasErrors()) {
            true -> {
                log.warn("Validation errors for order: ${errors.errorCount}")
                "orderForm"
            }
            else -> {
                log.info("Order submitted: $order")
                "redirect:/"
            }
        }


}