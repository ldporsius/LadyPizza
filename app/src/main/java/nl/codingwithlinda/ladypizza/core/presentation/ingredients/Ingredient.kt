package nl.codingwithlinda.ladypizza.core.presentation.ingredients

import nl.codingwithlinda.ladypizza.core.domain.model.Product
import nl.codingwithlinda.ladypizza.design.util.UiText

interface Ingredient: Product {
    override val id: String
        get() = id

    fun toUi(): UiText
}