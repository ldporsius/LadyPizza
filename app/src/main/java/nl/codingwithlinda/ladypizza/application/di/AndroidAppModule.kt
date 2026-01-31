package nl.codingwithlinda.ladypizza.application.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.FireStoreExtraToppingsRepository
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraToppingsPriceRepo
import nl.codingwithlinda.ladypizza.core.domain.repo.ExtraToppingsRepository
import nl.codingwithlinda.ladypizza.features.extra_toppings.presentation.ExtraToppingsViewModel

class AndroidAppModule: AppModule {

    val extraToppingsPriceRepo = ExtraToppingsPriceRepo()
    override val extraToppingsRepository: ExtraToppingsRepository
        get() = FireStoreExtraToppingsRepository(
            extraToppingsPriceRepo
        )

    val extraToppingsViewModel = viewModelFactory {
        initializer {
            ExtraToppingsViewModel(
                extraToppingsRepository
            )
        }
    }.create(ExtraToppingsViewModel::class.java)




}