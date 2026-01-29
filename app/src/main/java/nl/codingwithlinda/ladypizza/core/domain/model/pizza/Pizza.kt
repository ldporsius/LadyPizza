package nl.codingwithlinda.ladypizza.core.domain.model.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.Product
import nl.codingwithlinda.ladypizza.core.domain.model.ingredients.Ingredient
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

class Pizza(
    override val id: String,
): Product {

    private var mPrice: Double = 0.0

    private val toppings: MutableList<Ingredient> = mutableListOf()

    fun toppings(): List<Ingredient> = toppings.toList()

    fun addTopping(topping: Ingredient) {
        toppings.add(topping)
    }

    fun removeTopping(topping: Ingredient) {
        toppings.remove(topping)
    }


    fun getPrice(productPricing: ProductPricing): Double{
        return productPricing.getPrice(this.mPrice)
    }

    fun setPrice(price: Double){
        mPrice = price
    }

}