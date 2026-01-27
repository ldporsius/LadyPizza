package nl.codingwithlinda.ladypizza.core.domain.model.prices

interface ProductPricing {

    fun setPrice(price: Double): Double

    fun getPrice(price: Double): Double


}