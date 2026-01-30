package nl.codingwithlinda.ladypizza.core.presentation.drinks

import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

data class DrinkUi(
    val id: String,
    val name: ()-> UiText,
    val image: () -> UiImage,
    val price: () -> Double
)
