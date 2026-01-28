package nl.codingwithlinda.ladypizza.core.presentation.pizza

import nl.codingwithlinda.ladypizza.core.data.PizzaFactory
import nl.codingwithlinda.ladypizza.core.presentation.recipes.Margherita

class PizzaFactoryUi: PizzaFactory() {

    val menu = mutableListOf<PizzaUi>()

    fun erase(){
        menu.clear()
    }

    fun create(){
        val margherita = Margherita().createPizza().apply { setPrice(8.99, productPricing) }
        menu.add(margherita)
    }
}