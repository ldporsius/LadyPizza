package nl.codingwithlinda.ladypizza.core.data.repo

import nl.codingwithlinda.ladypizza.core.domain.model.ingredients.Ingredient
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza

data class PizzaDto(
    val id: String = "",
    val image_url: String = "",
    val ingredients: List<String> = emptyList(),
)

fun String.toIngredient(): Ingredient {
   return Ingredient(id = this)
}

fun PizzaDto.toDomain(): Pizza{
    return Pizza(
        id = id,
    ).apply {
        ingredients.onEach {
            addTopping(it.toIngredient())
        }

    }
}
