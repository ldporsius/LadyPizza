package nl.codingwithlinda.ladypizza.core.domain.model.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

class PizzaWithToppings(
    private val pizza: Pizza,
): ProductWithPricing {

    override val id: String
        get() = pizza.id + toppings.joinToString()

    private val toppings = mutableListOf<ExtraTopping>()

    fun extraToppingsUsed() = toppings.toList()

    fun addExtraTopping(topping: ExtraTopping) : PizzaWithToppings{
        this.toppings.add(topping)
        return this
    }
    fun removeExtraTopping(topping: ExtraTopping): PizzaWithToppings{
        this.toppings.remove(topping)
        return this
    }

    fun totalPrice(productPricing: ProductPricing): Double{
        val total = productPricing.getPrice(toppings.sumOf{ it.price })
        return pizza.getPrice(productPricing) + total
    }

    override fun price(productPricing: ProductPricing): Double {
        return totalPrice(productPricing)
    }
}