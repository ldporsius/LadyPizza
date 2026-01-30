package nl.codingwithlinda.ladypizza.core.data.drinks.repo

import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo

class LocalDrinkPriceRepo: PriceRepo {
    override fun getPrice(id: String): Double {
        return localDrinkPrices[id] ?: 10_000.0
    }
}

val localDrinkPrices = mapOf<String, Double>(
    "mineral_water" to 1.0,
    "seven_up" to 2.5
)