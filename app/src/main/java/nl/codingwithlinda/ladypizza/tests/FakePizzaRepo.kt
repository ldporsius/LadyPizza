package nl.codingwithlinda.ladypizza.tests

import nl.codingwithlinda.ladypizza.core.data.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository

class FakePizzaRepo: PizzaRepository {
    override suspend fun loadPizzas(): List<PizzaDto> {
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
        )
    }
}