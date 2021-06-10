package com.example.springinaction.tacos

import org.springframework.data.repository.CrudRepository

interface IngredientRepository : CrudRepository<Ingredient, Long>

interface TacoRepository : CrudRepository<Taco, Long>

interface OrderRepository : CrudRepository<Order, Long> {
    fun findOrderByDeliveryZip(deliveryZip: String)
}