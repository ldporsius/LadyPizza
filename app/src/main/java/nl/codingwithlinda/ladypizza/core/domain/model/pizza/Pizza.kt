package nl.codingwithlinda.ladypizza.core.domain.model.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.Product
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

abstract class Pizza(
    override val id: String,
): Product {

    private var mPrice: Double = 0.0

    private val toppings: MutableList<Product> = mutableListOf()

    fun toppings(): List<Product> = toppings.toList()

    fun addTopping(topping: Product) {
        toppings.add(topping)
    }

    fun removeTopping(topping: Product) {
        toppings.remove(topping)
    }


    fun getPrice(productPricing: ProductPricing): Double{
        return productPricing.getPrice(this.mPrice)
    }

    fun setPrice(price: Double){
        mPrice = price
    }

}