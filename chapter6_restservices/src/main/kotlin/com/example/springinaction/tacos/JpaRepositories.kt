package com.example.springinaction.tacos

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface IngredientRepository : CrudRepository<Ingredient, Long>

interface TacoRepository : CrudRepository<Taco, Long>

interface OrderRepository : PagingAndSortingRepository<Order, Long> {
    fun findOrderByDeliveryZip(deliveryZip: String): Order
    fun findByUserOrderByPlacedAtDesc(user: User, pageable: Pageable): List<Order>
}