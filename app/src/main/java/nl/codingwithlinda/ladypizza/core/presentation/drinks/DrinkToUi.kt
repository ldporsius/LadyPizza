package nl.codingwithlinda.ladypizza.core.presentation.drinks

import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink

fun Drink.toUi(): DrinkUi{
    return DrinkUi(
      product = this
    )
}
