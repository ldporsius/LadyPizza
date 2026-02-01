package nl.codingwithlinda.ladypizza.features.product_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaDetailRepository
import nl.codingwithlinda.ladypizza.core.presentation.pizza.MyPizza
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaUi

class ProductDetailViewModel(
    val savedStateHandle: SavedStateHandle,
    private val detailRepository: PizzaDetailRepository
): ViewModel() {

    companion object{
        val KEY_PIZZA_ID = "pizza_id"
    }
    val pizzaId = savedStateHandle.getStateFlow(KEY_PIZZA_ID, "")

    private val _pizzaFlow = MutableStateFlow<MyPizza?>(null)
    val mPizza = _pizzaFlow.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val pizzaFactory = PizzaFactoryUi()

    init {
        viewModelScope.launch {
            println("--- ProductDetailViewModel is loading a pizza from remote repo")
            launch {
                pizzaId.collect { id ->
                    try {
                        val pizza = detailRepository.loadPizza(id)
                        pizzaFactory.createUiPizza(pizza).let { pui ->
                            _pizzaFlow.update {
                                pui
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    fun buyExtraTopping(extraTopping: ExtraTopping){
        val pizza = mPizza.value ?: return
        println("--- ProductDetailViewModel buy extra topping: $extraTopping")

        val update = pizzaFactory.addExtraToppingToMyPizza(pizza, extraTopping)
        println("--- ProductDetailViewModel has updated pizza: ${pizza.pizza.extraToppingsUsed()}")
        println("The updated pizza is the same instance: ${pizza === update} ")
        println("The updated pizza is the same instance 2: ${pizza == update} ")
        _pizzaFlow.update {
           update
        }
    }
}