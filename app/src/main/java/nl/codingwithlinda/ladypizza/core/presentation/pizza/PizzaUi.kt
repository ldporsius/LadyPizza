package nl.codingwithlinda.ladypizza.core.presentation.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.UiText

abstract class PizzaUi(override val id: String): Pizza(id) {

    abstract val image: UiImage
    abstract fun description(): List<UiText>
}