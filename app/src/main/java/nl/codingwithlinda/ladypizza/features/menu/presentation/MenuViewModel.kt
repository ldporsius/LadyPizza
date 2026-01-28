package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.lifecycle.ViewModel
import nl.codingwithlinda.ladypizza.core.data.PizzaFactory
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi

class MenuViewModel(
    pizzaFactory: PizzaFactoryUi
): ViewModel() {


    val menu = pizzaFactory.menu
}