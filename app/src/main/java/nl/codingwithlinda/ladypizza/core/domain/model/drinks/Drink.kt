package nl.codingwithlinda.ladypizza.core.domain.model.drinks

import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

data class Drink(
    override val id: String,
    val price: Double
): ProductWithPricing {
    override fun price(productPricing: ProductPricing): Double {
        return productPricing.getPrice(this.price)
    }
}