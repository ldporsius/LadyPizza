package nl.codingwithlinda.ladypizza.features.extra_toppings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nl.codingwithlinda.ladypizza.core.domain.repo.ExtraToppingsRepository

class ExtraToppingsViewModel(
    val repository: ExtraToppingsRepository
): ViewModel() {

    private val _extraToppings = MutableStateFlow< List<ExtraToppingUi>>(emptyList())

    val extraToppings = _extraToppings.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _extraToppings.value)


    init {
        viewModelScope.launch {
            repository.loadExtraToppings().map {top ->
                ExtraToppingUi(top)
            }.run {
                _extraToppings.update {new ->
                    new
                }
            }
        }
    }
}