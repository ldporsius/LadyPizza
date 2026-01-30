package nl.codingwithlinda.ladypizza.core.presentation.product

import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.prices.symbol
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

abstract class ProductUi(val product: ProductWithPricing){

    abstract val image: UiImage

    abstract fun name(): UiText

    abstract fun description(): List<UiText>

    fun priceUi(productPricing: ProductPricing): UiText =
        UiText.DynamicText(productPricing.symbol() + product.price(productPricing).toString())

}