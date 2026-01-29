package nl.codingwithlinda.ladypizza.core.presentation.ingredients

import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.design.util.UiText

data class Ingredient(
    override val id: String,
    val toUI: ()-> UiText
): IngredientUi {
    override fun toUi(): UiText {
        return toUI()
    }
}

fun String.toIngredientUi(): UiText{
    val allIngredient = sauceIngredientsUi + cheeseIngredientsUi + veggieIngredientsUi
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
