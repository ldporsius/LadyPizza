package nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings

import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.Ingredient
import nl.codingwithlinda.ladypizza.design.util.UiText


val extraMozzarellaCheese = ExtraTopping(
    Ingredient(
        id = "extra_cheese",
        toUI = { UiText.StringResourceText(R.string.mozzarella_cheese) }
    )
    , 1.00)