package nl.codingwithlinda.ladypizza.core.presentation.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.prices.symbol
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

abstract class PizzaUi(override val id: String): Pizza(id) {

    abstract val image: UiImage

    abstract fun name(): UiText

    abstract fun description(): List<UiText>

    fun price(productPricing: ProductPricing): UiText =
        UiText.DynamicText(productPricing.symbol() + getPrice(productPricing).toString())



}