package nl.codingwithlinda.ladypizza.features.product_detail.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.FireStorePizzaDetailRepository
import nl.codingwithlinda.ladypizza.features.extra_toppings.presentation.ExtraToppingsScreen
import nl.codingwithlinda.ladypizza.features.extra_toppings.presentation.ExtraToppingsViewModel
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailViewModel.Companion.KEY_PIZZA_ID

@Composable
fun ProductDetailRoot(
    key: String,
    extraToppingsViewModel: ExtraToppingsViewModel,
    navBack: () -> Unit
) {
    val detailRepo = FireStorePizzaDetailRepository()

    val detailViewmodel = viewModel<ProductDetailViewModel>(
        factory = viewModelFactory {
            initializer {
                ProductDetailViewModel(
                    savedStateHandle = createSavedStateHandle().apply {
                        this.set(KEY_PIZZA_ID, key)
                    },
                    detailRepository = detailRepo
                )
            }
        }
    )

    LaunchedEffect(key) {
        detailViewmodel.savedStateHandle[KEY_PIZZA_ID] = key
    }

    ProductDetailScreen(
        detailViewmodel = detailViewmodel,
        extraToppings = {
            ExtraToppingsScreen(
                extraToppings = extraToppingsViewModel.extraToppings.collectAsStateWithLifecycle().value
            )},
        navBack = navBack
    )
}