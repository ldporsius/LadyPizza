package nl.codingwithlinda.ladypizza.core.presentation.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.Ingredient
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.IngredientUi
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.toIngredientUi
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

class MyPizza(
    private val imageUrl: String,
    private val myname: ()-> UiText,
    override val pizza: Pizza
) : PizzaUi( pizza){

    private val ingredients: List<IngredientUi> = pizza.toppings().map {
        val uitext = it.id.toIngredientUi()
        Ingredient(
            id = it.id,
            toUI = { uitext }
        )
    }


    override val image: UiImage
        get() = UiImage.UrlImage(imageUrl)

    override fun name(): UiText {
        return myname()
    }

    override fun description(): List<UiText>{
       return this.ingredients.map {
            it.toUi()
        }
    }

}