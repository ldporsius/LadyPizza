package nl.codingwithlinda.ladypizza.core.domain.model.ingredients

import nl.codingwithlinda.ladypizza.core.domain.model.Product

data class MozzarellaCheese(
    override val id: String,
    override val name: String,
): Product
