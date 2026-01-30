package nl.codingwithlinda.ladypizza.core.presentation.pizza

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.data.pizza.PizzaFactory
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.LocalPriceRepo
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.toIngredient
import nl.codingwithlinda.ladypizza.core.domain.images.ProductImageBuffer
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo
import nl.codingwithlinda.ladypizza.design.util.UiText

class PizzaFactoryUi(
    private val priceRepo: PriceRepo = LocalPriceRepo()
): PizzaFactory() {

    val menuObservable = MutableStateFlow<List<PizzaUi>>(emptyList())
    private val menu = mutableListOf<PizzaUi>()

    fun erase(){
        menu.clear()
    }

    fun createUiPizza(pizza: Pizza){
       pizzaUi(pizza)?.let {pui ->
           println("-- PizzaFactoryUi created pizzaUI from pizza: ${pizza.id} with toppings: ${pizza.toppings()}. pui_desc: ${pui.description()}")
            menu.add(pui)
            menuObservable.update {
                menu.toList()
            }
        }
    }
    private fun pizzaUi(pizza: Pizza): PizzaUi?{
        return MyPizza(
            pizza = pizza.apply {
                setPrice(priceRepo.getPrice(pizza.id))
            },
            myname = {
                pizzaNames.getOrElse(pizza.id, {UiText.DynamicText(pizza.id)})
            },
            imageUrl = ProductImageBuffer.getImageByProductId(pizza.id)?.imageUrl ?: ""
        )
    }


}

