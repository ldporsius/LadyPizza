package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import nl.codingwithlinda.ladypizza.core.domain.model.prices.Currency
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi

class MenuViewModel(
    val pizzaRepo : PizzaRepository
): ViewModel() {


    val pizzaFactory: PizzaFactoryUi = PizzaFactoryUi()

    val menu = pizzaFactory.menuObservable
    init {

        viewModelScope.launch {
          pizzaRepo.loadPizzas().onEach {
              pizzaFactory.create(it)
          }
        }
    }


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