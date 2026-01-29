package nl.codingwithlinda.ladypizza.core.presentation.pizza

import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.design.util.UiText

val pizzaNames = mapOf<String, UiText>(
    "margherita" to UiText.DynamicText("Margherita"),
    "pepperoni" to UiText.DynamicText("Pepperoni"),
    "hawaiian" to UiText.DynamicText("Hawaiian"),
    "bbq_chicken" to UiText.StringResourceText(R.string.bbq_chicken),
    "four_cheese" to UiText.StringResourceText(R.string.four_cheese),
    "veggie_delight" to UiText.StringResourceText(R.string.veggie_delight),
    "meat_lovers" to UiText.StringResourceText(R.string.meat_lovers),
    "spicy_inferno" to UiText.StringResourceText(R.string.spicy_inferno),
    "seafood_special" to UiText.StringResourceText(R.string.seafood_special),
    "truffle_mushroom" to UiText.StringResourceText(R.string.truffle_mushroom)
)