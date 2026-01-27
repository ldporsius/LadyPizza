package nl.codingwithlinda.ladypizza.core.domain.model.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.Product
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

class Pizza(
    override val id: String,
): Product {

    private var mPrice: Double = 0.0

    private val toppings: MutableList<Product> = mutableListOf()

    fun addTopping(topping: Product) {
        toppings.add(topping)
    }

    fun removeTopping(topping: Product) {
        toppings.remove(topping)
    }


    fun getPrice(productPricing: ProductPricing): Double{
        return productPricing.getPrice(this.mPrice)
    }

    fun setPrice(price: Double, productPricing: ProductPricing){
        mPrice = productPricing.setPrice(price)
    }

}