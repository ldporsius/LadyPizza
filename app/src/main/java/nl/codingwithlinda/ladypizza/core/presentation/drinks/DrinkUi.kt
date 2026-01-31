package nl.codingwithlinda.ladypizza.core.presentation.drinks

import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.prices.formatLocale
import nl.codingwithlinda.ladypizza.core.presentation.prices.symbol
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

data class DrinkUi(
    val id: String,
    val name: ()-> UiText,
    val image: () -> UiImage,
    val price: Double
){
    fun priceUi(pricing: ProductPricing) : UiText{
        return UiText.DynamicText(pricing.formatLocale(price = price))
    }
}
