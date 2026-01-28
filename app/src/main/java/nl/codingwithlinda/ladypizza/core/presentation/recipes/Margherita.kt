package nl.codingwithlinda.ladypizza.core.presentation.recipes

import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.Ingredient
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.basil
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.mozzarellaCheese
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.oliveOil
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.tomatoSauce
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaUi
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

class Margherita() : PizzaUi( "margherita"){

    private val ingredients: List<Ingredient> = mutableListOf(
        tomatoSauce,
        mozzarellaCheese,
        basil,
        oliveOil
    )

    override fun name(): UiText = UiText.DynamicText("Margherita")



    fun createPizza(): PizzaUi{
        return this.apply {
            ingredients.onEach {
                addTopping(it)
            }
        }
    }

    override val image: UiImage
        get() = UiImage.ResourceImage(R.drawable.ic_launcher_foreground)

    override fun description(): List<UiText>{
       return this.ingredients.map {
            it.toUi()
        }
    }

}

