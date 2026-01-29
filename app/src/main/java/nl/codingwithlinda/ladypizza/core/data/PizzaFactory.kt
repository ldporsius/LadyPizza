package nl.codingwithlinda.ladypizza.core.data

import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings


abstract class PizzaFactory {

    fun addExtraTopping(pizza: Pizza, topping: ExtraTopping): PizzaWithToppings{
        return PizzaWithToppings(
            pizza = pizza,
        ).apply {
            addExtraTopping(topping)
        }
    }
    fun removeExtraTopping(pizzaWithToppings: PizzaWithToppings, topping: ExtraTopping): PizzaWithToppings{
        return pizzaWithToppings.apply {
            this.removeExtraTopping(topping)
        }
    }


}