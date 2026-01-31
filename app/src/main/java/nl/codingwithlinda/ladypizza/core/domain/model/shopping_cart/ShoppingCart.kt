package nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart

import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

class ShoppingCart(
    var pricing: ProductPricing
) {

    private val _items = mutableListOf<ProductWithPricing>()

    fun items() = _items.toList()

    fun itemsGrouped() = _items.groupBy {
        it.id
    }

    fun putInCart(item: ProductWithPricing){
        _items.add(item)
    }

    fun total(): Double{
       return _items.sumOf {
           it.price(pricing)
       }
    }
}
