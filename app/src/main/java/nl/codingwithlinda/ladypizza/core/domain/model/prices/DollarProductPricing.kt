package nl.codingwithlinda.ladypizza.core.domain.model.prices

class DollarProductPricing(
    private val exchangeRate: Double
): ProductPricing {

    override fun setPrice(price: Double): Double {
        return price
    }

    override fun getPrice(price: Double): Double {
       return price * exchangeRate
    }

}