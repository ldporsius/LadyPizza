package nl.codingwithlinda.ladypizza.core.presentation.drinks

import nl.codingwithlinda.ladypizza.core.domain.images.ProductImageBuffer
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

fun Drink.toUi(): DrinkUi{
    return DrinkUi(
        id = this.id,
        name = {
            UiText.DynamicText(this.id)
        },
        image = {
            val imageUrl = ProductImageBuffer.getImageByProductId(this.id)?.imageUrl ?: ""
            UiImage.UrlImage(imageUrl)
        },
        price ={
            this.price
        }
    )
}