package nl.codingwithlinda.ladypizza.core.domain.model.drinks

interface DrinksRepo {
    suspend fun loadDrinks(): List<Drink>
}