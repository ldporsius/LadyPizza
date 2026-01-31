package nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings


class ExtraToppingsFactory {
    /*
    bacon
    basil
     */
    val prices = mapOf<String, Double>(
        "bacon" to .5,
        "basil" to .5,
        "cheese" to 1.0
    )
    fun createExtraToppingFromId(id: String): ExtraTopping{
        return ExtraTopping(
            id = id,
            price = prices.getOrElse(id, { 0.0 })
        )
    }
}