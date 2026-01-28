package nl.codingwithlinda.ladypizza.core.data

import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings
import nl.codingwithlinda.ladypizza.core.presentation.recipes.Margherita
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing


abstract class PizzaFactory {

    //open val productPricing = EuroProductPricing()


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