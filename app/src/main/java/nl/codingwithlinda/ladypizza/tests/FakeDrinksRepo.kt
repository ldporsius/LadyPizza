package nl.codingwithlinda.ladypizza.tests

import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.DrinksRepo
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo

class FakeDrinksRepo(
    private val priceRepo: PriceRepo
): DrinksRepo {
    override suspend fun loadDrinks(): List<Drink> {
        return listOf(
            Drink(
                id = "mineral_water",
                price = priceRepo.getPrice("mineral_water")
            )
        )
    }
}