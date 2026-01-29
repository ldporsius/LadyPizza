package nl.codingwithlinda.ladypizza.core.presentation.pizza.sorting

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza

interface PizzaSortOrder<T> {
    fun sortBy(): List<T>
}