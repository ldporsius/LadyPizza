package nl.codingwithlinda.ladypizza.features.product_detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaDetailRepository
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

    private val _pizzaFlow = MutableStateFlow<PizzaUi?>(null)
    val mPizza = _pizzaFlow.stateIn(viewModelScope, SharingStarted.Companion.WhileSubscribed(5000), _pizzaFlow.value)

    val pizzaFactory = PizzaFactoryUi()

    init {
        viewModelScope.launch {
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