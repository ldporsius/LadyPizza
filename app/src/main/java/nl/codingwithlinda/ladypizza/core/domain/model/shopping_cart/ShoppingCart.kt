package nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

class ShoppingCart(
    var pricing: ProductPricing
) {

    private val _items = mutableListOf<ProductWithPricing>()

    private val _cartObservable = MutableStateFlow<List<ProductWithPricing>>(emptyList())
    val cartObservable = _cartObservable.asStateFlow()

    fun items() = _items.toList()

    fun itemsGrouped() = _items.groupBy {
        it.id
    }

    fun putInCart(item: ProductWithPricing){
        _items.add(item)
        _cartObservable.update {
           _items.toList()
        }
    }

    fun totalNumberOfItem(id: String) = _items.count {
        it.id == id
    }
    val totalNumberOfItemFlow = cartObservable.map { list ->
        list.groupBy {
            it.id
        }.map {
            it.key to it.value.size
        }
    }


    fun total(): Double{
       return _items.sumOf {
           it.price(pricing)
       }
    }
}
