package nl.codingwithlinda.ladypizza.core.data.repo

import nl.codingwithlinda.ladypizza.core.domain.model.pizza.pizzaPrices
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo

class LocalPriceRepo: PriceRepo {
    override fun getPrice(id: String): Double {
        return pizzaPrices.getOrElse(id, {0.0})
    }
}