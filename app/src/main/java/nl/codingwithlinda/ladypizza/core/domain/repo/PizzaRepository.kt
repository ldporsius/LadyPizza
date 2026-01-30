package nl.codingwithlinda.ladypizza.core.domain.repo

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza

interface PizzaRepository {

    suspend fun loadPizzas(): List<Pizza>
}