package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.lifecycle.ViewModel
import nl.codingwithlinda.ladypizza.core.domain.model.prices.Currency
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi

class MenuViewModel(
    private val pizzaFactory: PizzaFactoryUi
): ViewModel() {


    init {
        pizzaFactory.create()
    }
    fun menu() = pizzaFactory.menu.toList()

    fun exchangeRate(currency: Currency): Double =
        when(currency){
            Currency.EURO -> 1.0
            Currency.DOLLAR -> 1.2
        }
    fun productPricing(currency: Currency): ProductPricing = when(currency){
        Currency.EURO -> EuroProductPricing()
        Currency.DOLLAR -> DollarProductPricing(exchangeRate(currency))
    }
}