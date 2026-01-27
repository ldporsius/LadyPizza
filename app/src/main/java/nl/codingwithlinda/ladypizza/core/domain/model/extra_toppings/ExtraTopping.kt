package nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings

import nl.codingwithlinda.ladypizza.core.domain.model.Product

data class ExtraTopping(
    val topping: Product,
    val price: Double
)