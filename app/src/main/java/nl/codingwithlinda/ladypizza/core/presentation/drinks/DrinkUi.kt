package nl.codingwithlinda.ladypizza.core.presentation.drinks

import nl.codingwithlinda.ladypizza.core.domain.images.ProductImageBuffer
import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.toIngredientUi
import nl.codingwithlinda.ladypizza.core.presentation.prices.formatLocale
import nl.codingwithlinda.ladypizza.core.presentation.prices.symbol
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

class DrinkUi(val product: ProductWithPricing){

    private val imageUrl = ProductImageBuffer.getImageByProductId(product.id)?.imageUrl ?: ""
    val image: UiImage = UiImage.UrlImage(imageUrl)

    fun name(): UiText = product.id.replace("_extra", "").toIngredientUi()

    fun description(): List<UiText> = emptyList()

    fun priceUi(productPricing: ProductPricing): UiText =
        UiText.DynamicText(productPricing.formatLocale(price = product.price(productPricing)))

}