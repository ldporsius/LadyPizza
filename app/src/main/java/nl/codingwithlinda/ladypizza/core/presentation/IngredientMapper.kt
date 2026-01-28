package nl.codingwithlinda.ladypizza.core.presentation

import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.tomatoSauce
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.design.util.UiText

fun tomatoSauce.ToUi(): UiText{
    return UiText.StringResourceText(R.string.tomato_sauce)
}


fun translatePizza(pizza: Pizza): UiText{
    pizza.toppings().onEach {
        if (it.id == "tomatoSauce") {
            return UiText.StringResourceText(R.string.tomato_sauce)
        }
    }

    return UiText.DynamicText("Pizza")

}