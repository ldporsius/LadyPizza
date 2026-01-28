package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.lifecycle.ViewModel
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi

class MenuViewModel(
    private val pizzaFactory: PizzaFactoryUi
): ViewModel() {


    init {
        pizzaFactory.create()
    }
    fun menu() = pizzaFactory.menu.toList()
}