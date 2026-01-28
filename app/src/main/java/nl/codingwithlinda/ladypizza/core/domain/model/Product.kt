package nl.codingwithlinda.ladypizza.core.domain.model

import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.design.util.UiText

interface Product {
    val id: String
}
interface ProductWithPricing: Product {
    override val id: String
        get() = id

    fun price(productPricing: ProductPricing): Double
}