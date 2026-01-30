package nl.codingwithlinda.ladypizza.tests

import nl.codingwithlinda.ladypizza.core.data.pizza.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.toDomain
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository

class FakePizzaRepo: PizzaRepository {
    override suspend fun loadPizzas(): List<Pizza> {
        return listOf(
            PizzaDto(
                id = "margherita",
                image_url = "",
                ingredients = listOf("mozzarella_cheese", "tomato_sauce")
            ),
            PizzaDto(
                id = "bbq_chicken",
                image_url = "",
                ingredients = listOf("grilled_chicken", "tomato_sauce")
            )
        ).map {
            it.toDomain()
        }
    }
}