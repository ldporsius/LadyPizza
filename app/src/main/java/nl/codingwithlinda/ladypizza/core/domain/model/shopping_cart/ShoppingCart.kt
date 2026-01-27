package nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

class ShoppingCart(
    var pricing: ProductPricing
) {

    private val _items = mutableListOf<PizzaWithToppings>()

    fun items() = _items.toList()

    fun itemsGrouped() = _items.groupBy {
        it.id
    }


    fun buyPizza(pizza: PizzaWithToppings, amount: Int) {
        repeat(amount) {
            _items.add(pizza)
        }
    }

    fun total(): Double{
       return _items.sumOf {
           it.price(pricing)
       }
    }
}
