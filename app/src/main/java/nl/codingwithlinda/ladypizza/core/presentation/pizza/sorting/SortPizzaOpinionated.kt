package nl.codingwithlinda.ladypizza.core.presentation.pizza.sorting

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaUi

class SortPizzaOpinionated(
    private val pizzas: List<PizzaUi>
): PizzaSortOrder<PizzaUi> {
    override fun sortBy() : List<PizzaUi>{
        val order = mapOf<String, Int>(
                "margherita" to 0,
                "pepperoni" to 1,
                "hawaiian" to 2,
                "bbq_chicken" to 3,
                "four_cheese" to 4,
                "veggie_delight" to 5,
                "meat_lovers" to 6,
                "spicy_inferno" to 7,
                "seafood_special" to 8,
                "truffle_mushroom" to 9
        )

        val sortedPizzas = pizzas.map { pizza ->
           val order =  order[pizza.id()] ?: error("unknown pizza id")
            order to pizza
        }.sortedBy {
            it.first
        }.map {
            it.second
        }

        return sortedPizzas
    }
}