package nl.codingwithlinda.ladypizza.features.menu.presentation.state

import nl.codingwithlinda.ladypizza.core.presentation.drinks.DrinkUi

data class DrinkShoppingCartState(
    val drink: DrinkUi,
    val quantity: Int
)
