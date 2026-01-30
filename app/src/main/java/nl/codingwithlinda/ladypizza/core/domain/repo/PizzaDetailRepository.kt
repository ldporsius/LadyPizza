package nl.codingwithlinda.ladypizza.core.domain.repo

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza

interface PizzaDetailRepository {

    suspend fun loadPizza(id: String): Pizza
}