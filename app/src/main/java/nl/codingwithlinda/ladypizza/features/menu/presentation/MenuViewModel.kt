package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.ladypizza.application.LadyPizzaApplication
import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.DrinksRepo
import nl.codingwithlinda.ladypizza.core.domain.model.prices.Currency
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart.ShoppingCart
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository
import nl.codingwithlinda.ladypizza.core.presentation.drinks.DrinkUi
import nl.codingwithlinda.ladypizza.core.presentation.drinks.toUi
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi
import nl.codingwithlinda.ladypizza.core.presentation.pizza.sorting.SortPizzaOpinionated
import nl.codingwithlinda.ladypizza.features.menu.presentation.state.DrinkShoppingCartState

class MenuViewModel(
    private val pizzaRepo : PizzaRepository,
    private val drinksRepo: DrinksRepo,
    private val shoppingCart: ShoppingCart
): ViewModel() {


    private val pizzaFactory: PizzaFactoryUi = PizzaFactoryUi()

    val menu = pizzaFactory.menuObservable.map {pizzas->
        val sortingStrategy = SortPizzaOpinionated(pizzas)
        sortingStrategy.sortBy()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _drinks = MutableStateFlow<List<DrinkUi>>(emptyList())

    init {
        viewModelScope.launch {
          pizzaRepo.loadPizzas().onEach {
              pizzaFactory.addUiPizzaToMenu(it)
          }
        }
        viewModelScope.launch {
            drinksRepo.loadDrinks().let {new ->
                println("loaded drinks $new")
                _drinks.update {
                    new.map {
                        it.toUi()
                    }
                }
            }
        }
    }

    fun putInCart(item: ProductWithPricing){
        shoppingCart.putInCart(item)
    }

    fun removeFromCart(item: ProductWithPricing){
        shoppingCart.removeFromCart(item)
    }

    fun removeAllFromCart(item: ProductWithPricing){
       shoppingCart.removeAllFromCart(item)
    }


    val cartState = shoppingCart.totalNumberOfItemFlow

    val drinks = _drinks.combine(cartState){
        drinks, cart ->
        drinks.map {
            DrinkShoppingCartState(
                drink = it,
                quantity = cart.find { pair ->
                    pair.first == it.product.id
                }?.second ?: 0
            )
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


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