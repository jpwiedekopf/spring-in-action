package com.example.springinaction.tacos

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@Profile("!initdb")
class OrderController(
    @Autowired val orderRepository: OrderRepository,
    @Autowired val orderProperties: OrderProperties
) {

    private val log = LoggerFactory.getLogger(OrderController::class.java)

    @GetMapping("/current")
    fun orderForm(
        @AuthenticationPrincipal user: User,
        @ModelAttribute order: Order
    ): String {
        if (order.deliveryName == null) order.deliveryName = user.fullName
        if (order.deliveryCity == null) order.deliveryCity = user.city
        if (order.deliveryState == null) order.deliveryState = user.state
        if (order.deliveryStreet == null) order.deliveryStreet = user.street
        if (order.deliveryZip == null) order.deliveryZip = user.zip
        log.info("prepared order form for $user")
        return "orderForm"
    }

    @GetMapping
    fun ordersForUser(@AuthenticationPrincipal user: User, model: Model): String =
        PageRequest.of(0, orderProperties.pageSize).let { pr ->
            val orderList = orderRepository.findByUserOrderByPlacedAtDesc(user, pr)
            model["orders"] = orderList
            model["user"] = user
            return "orderList"
        }

    @PostMapping
    fun processOrder(
        @Valid order: Order,
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

                order.user = user
                orderRepository.save(order)
                log.info("Order submitted for user ${user.username}: $order")
                sessionStatus.setComplete()
                "redirect:/orders"
            }
        }
}

@ConstructorBinding
@ConfigurationProperties(prefix = "taco.orders")
@Validated
data class OrderProperties(
    @field:Min(value = 5, message = "must be between 5 and 25")
    @field:Max(value = 25, message = "must be between 5 and 25")
    val pageSize: Int
)