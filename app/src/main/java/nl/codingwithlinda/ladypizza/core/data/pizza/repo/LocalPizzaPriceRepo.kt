package nl.codingwithlinda.ladypizza.core.data.pizza.repo
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.pizzaPrices
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo

class LocalPizzaPriceRepo: PriceRepo {
    override fun getPrice(id: String): Double {
        return pizzaPrices.getOrElse(id, {0.0})
    }
}