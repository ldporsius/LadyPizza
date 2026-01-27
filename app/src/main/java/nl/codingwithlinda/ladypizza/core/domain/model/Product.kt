package nl.codingwithlinda.ladypizza.core.domain.model

import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing

interface Product {
    val id: String
    val name: String
}
interface ProductWithPricing: Product {
    override val id: String
        get() = id
    override val name: String
        get() = name
    fun price(productPricing: ProductPricing): Double
}