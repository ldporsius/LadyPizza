package nl.codingwithlinda.ladypizza.core.data.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings

abstract class PizzaFactory {

    fun addExtraTopping(pizza: PizzaWithToppings, topping: ExtraTopping): PizzaWithToppings {
        return pizza.apply {
            addExtraTopping(topping)
        }
    }
    fun removeExtraTopping(pizzaWithToppings: PizzaWithToppings, topping: ExtraTopping): PizzaWithToppings {
        return pizzaWithToppings.apply {
            this.removeExtraTopping(topping)
        }
    }


}