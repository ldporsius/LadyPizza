package nl.codingwithlinda.ladypizza.core.data

import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings
import nl.codingwithlinda.ladypizza.core.presentation.recipes.Margherita
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing


object PizzaFactory {

    val productPricing = EuroProductPricing()


    val menu = mutableListOf<Pizza>()

    fun erase(){
        menu.clear()
    }

    fun create(){
        val margherita = Margherita().createPizza().apply { setPrice(8.99, productPricing) }
        menu.add(margherita)
    }


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