package nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings

import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo


class ExtraToppingsPriceRepo: PriceRepo {
    /*
    bacon
    basil
     */
    val prices = mapOf<String, Double>(
        "bacon" to .5,
        "basil" to .5,
        "cheese" to 1.0
    )
    /*fun createExtraToppingFromId(id: String): ExtraTopping{
        return ExtraTopping(
            id = id,
            price = prices.getOrElse(id, { 0.0 })
        )
    }*/

    override fun getPrice(id: String): Double  = prices.getOrElse(id, { 0.0 })
}