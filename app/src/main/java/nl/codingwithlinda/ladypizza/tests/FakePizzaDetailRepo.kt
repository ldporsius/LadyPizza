package nl.codingwithlinda.ladypizza.tests

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaDetailRepository

class FakePizzaDetailRepo: PizzaDetailRepository {
    override suspend fun loadPizza(id: String): Pizza {
        return Pizza(
            id = "margherita"
        )
    }
}