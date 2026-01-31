package nl.codingwithlinda.ladypizza.core.presentation.ingredients

import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.core.domain.model.Product
import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.design.util.UiText




fun String.toIngredientUi(): UiText{
    val allIngredient =
        sauceIngredientsUi
        .plus(oilIngredientsUi)
        .plus(cheeseIngredientsUi )
        .plus(veggieIngredientsUi)
        .plus(meatIngredientsUi)
    return allIngredient.getOrElse(this, {UiText.DynamicText(this)})
}
val sauceIngredientsUi = mapOf<String, UiText>(
    "tomato_sauce" to UiText.StringResourceText(R.string.tomato_sauce),
    "bbq_sauce" to UiText.StringResourceText(R.string.bbq_sauce),
    "cream" to UiText.StringResourceText(R.string.cream),
)
val cheeseIngredientsUi = mapOf<String, UiText>(
    "mozzarella_cheese" to UiText.StringResourceText(R.string.mozzarella_cheese),
    "gorgonzola" to UiText.StringResourceText(R.string.gorgonzola),
    "parmesan" to UiText.StringResourceText(R.string.parmesan),
    "ricotta" to UiText.StringResourceText(R.string.ricotta),
)
val oilIngredientsUi = mapOf<String, UiText>(
    "olive_oil" to UiText.StringResourceText(R.string.olive_oil),
    "truffle_oil" to UiText.StringResourceText(R.string.truffle_oil),
)
val veggieIngredientsUi = mapOf<String, UiText>(
    "basil" to UiText.StringResourceText(R.string.basil),
    "pineapple" to UiText.StringResourceText(R.string.pineapple),
    "onion" to UiText.StringResourceText(R.string.onion),
    "corn" to UiText.StringResourceText(R.string.corn),
    "mushrooms" to UiText.StringResourceText(R.string.mushrooms),
    "olives" to UiText.StringResourceText(R.string.olives),
    "bell_pepper" to UiText.StringResourceText(R.string.bell_pepper),
    "jalapenos" to UiText.StringResourceText(R.string.jalapenos),
    "red_chili_pepper" to UiText.StringResourceText(R.string.red_chili_pepper),
    "garlic" to UiText.StringResourceText(R.string.garlic),
    "parsley" to UiText.StringResourceText(R.string.parsley),
)
val meatIngredientsUi = mapOf<String, UiText>(
    "pepperoni" to UiText.StringResourceText(R.string.pepperoni),
    "ham" to UiText.StringResourceText(R.string.ham),
    "grilled_chicken" to UiText.StringResourceText(R.string.grilled_chicken),
    "bacon" to UiText.StringResourceText(R.string.bacon),
    "sausage" to UiText.StringResourceText(R.string.sausage),
    "spicey_salami" to UiText.StringResourceText(R.string.spicey_salami),
)

val remoteIngredientsIds = listOf(
    //cheese//
    "mozzarella_cheese",
    "gorgonzola",
    "parmesan",
    "ricotta",

    //sauce//
    "tomato_sauce",
    "bbq_sauce",
    "cream",

//veggies and fruit//
    "basil",
    "pineapple",
    "onion",
    "corn",
    "mushrooms",
    "olives",
    "bell_pepper",
    "jalapenos",
    "red_chili_pepper",
    "garlic",
    "parsley",

//oil//
    "olive_oil",
    "truffle_oil",

//meat//
    "pepperoni",
    "ham",
    "grilled_chicken",
    "bacon",
    "sausage",
    "spicey_salami",

//fish//
    "shrimp",
    "mussels",
    "squid",
)
