package nl.codingwithlinda.ladypizza.core.presentation.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.PizzaWithToppings
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.toIngredientUi
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

data class MyPizza(
    private val imageUrl: String,
    private val myname: ()-> UiText,
    override val pizza: PizzaWithToppings
) : PizzaUi(pizza){

    fun pizzaId () = pizza.pizza.id
    private val ingredients: List<UiText> = pizza.pizza
        .toppings().map {
        it.id.toIngredientUi()

    }

    override val image: UiImage
        get() = UiImage.UrlImage(imageUrl)

    override fun name(): UiText {
        return myname()
    }

    override fun description(): List<UiText>{
       return this.ingredients
    }

    fun extraToppings(): List<Pair<String,UiText>>{
        val xtras = this.pizza.extraToppingsUsed().map {
            it.id.replace("_extra", "")
        }.groupBy {
            it
        }.map {
             it.key to it.value.size
        }.toMap()
        val text = xtras.map {
            Pair( it.value.toString() , it.key.toIngredientUi() )
        }
        return text

    }

}