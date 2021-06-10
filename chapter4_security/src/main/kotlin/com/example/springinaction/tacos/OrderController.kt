package com.example.springinaction.tacos

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
//@SessionAttributes("order")
class OrderController(
    @Autowired val orderRepository: OrderRepository
) {

    private val log = LoggerFactory.getLogger(OrderController::class.java)

    @GetMapping("/current")
    fun orderForm(model: Model): String {
        model["order"] = Order()
        return "orderForm"
    }

    @PostMapping
    fun processOrder(
        @Valid @ModelAttribute("order") order: Order,
        errors: Errors,
        sessionStatus: SessionStatus,
        @AuthenticationPrincipal user: User
    ) =
        when (errors.hasErrors()) {
            true -> {
                log.warn("Validation errors for order: ${errors.errorCount}")
                "orderForm"
            }
            else -> {
                log.info("Order submitted for user ${user.username}: $order")
                order.user = user
                orderRepository.save(order)
                sessionStatus.setComplete()
                "redirect:/"
            }
        }


}