package nl.codingwithlinda.ladypizza.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import nl.codingwithlinda.ladypizza.application.LadyPizzaApplication
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.FireStorePizzaDetailRepository
import nl.codingwithlinda.ladypizza.features.extra_toppings.presentation.ExtraToppingsViewModel
import nl.codingwithlinda.ladypizza.features.menu.presentation.MenuListScreen
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailRoot
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailScreen
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailViewModel
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailViewModel.Companion.KEY_PIZZA_ID

@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(Menu)

    NavDisplay(
        backStack = backStack,
        entryProvider = {key ->
            NavEntry(key){navKey ->
                when(key){
                    is Menu -> MenuListScreen(
                        navToDetail = {id -> backStack.add(ProductDetail(id))}
                    )
                    is ProductDetail -> {

                        val detailRepo = FireStorePizzaDetailRepository()

                        val detailViewmodel = viewModel<ProductDetailViewModel>(
                            factory = viewModelFactory {
                                initializer {
                                    ProductDetailViewModel(
                                        savedStateHandle = createSavedStateHandle().apply {
                                            this.set(KEY_PIZZA_ID, key.id)
                                        },
                                        detailRepository = detailRepo
                                    )
                                }
                            }
                        )
                        val extraToppingsViewModel = viewModel<ExtraToppingsViewModel>(
                            factory = viewModelFactory {
                                initializer {
                                    ExtraToppingsViewModel(
                                        repository = LadyPizzaApplication.appModule.extraToppingsRepository,
                                        shoppingCart = LadyPizzaApplication.shoppingCart
                                    )
                                }
                            }
                        )
                        ProductDetailRoot(
                            key.id,
                            detailViewmodel = detailViewmodel,
                            extraToppingsViewModel = extraToppingsViewModel,
                            navBack = {backStack.retainAll(listOf(Menu))}
                        )
                    }
                }
            }
        }
    )
}