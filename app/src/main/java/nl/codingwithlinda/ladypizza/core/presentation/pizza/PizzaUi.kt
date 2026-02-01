package nl.codingwithlinda.ladypizza.core.presentation.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.prices.symbol
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

abstract class PizzaUi(open val pizza: PizzaWithToppings) {

    fun id() = pizza.id
    abstract val image: UiImage

    abstract fun name(): UiText

    abstract fun description(): List<UiText>

    fun price(productPricing: ProductPricing): UiText =
        UiText.DynamicText(productPricing.symbol() + pizza.price(productPricing).toString())

}