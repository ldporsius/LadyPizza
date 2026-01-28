package nl.codingwithlinda.ladypizza.core.domain.model.prices

sealed interface ProductPricing {

    fun setPrice(price: Double): Double

    fun getPrice(price: Double): Double


}