package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.ladypizza.application.LadyPizzaApplication
import nl.codingwithlinda.ladypizza.core.data.drinks.repo.FireStoreDrinksRepo
import nl.codingwithlinda.ladypizza.core.data.drinks.repo.LocalDrinkPriceRepo
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.FirestorePizzaRepository
import nl.codingwithlinda.ladypizza.core.domain.model.prices.Currency
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.features.menu.presentation.components.CardContainer
import nl.codingwithlinda.ladypizza.features.menu.presentation.components.DrinkCard
import nl.codingwithlinda.ladypizza.features.menu.presentation.components.DrinkCardIsInCart
import nl.codingwithlinda.ladypizza.features.menu.presentation.components.PizzaCard

@Composable
fun MenuListScreen(
    navToDetail: (String) -> Unit,
    modifier: Modifier = Modifier) {

    val priceRepo = LocalDrinkPriceRepo()
    val pizzaRepo = FirestorePizzaRepository()
    val drinksRepo = FireStoreDrinksRepo(priceRepo)

    val menuViewModel = viewModel<MenuViewModel>(
        factory = viewModelFactory {
            initializer {
                MenuViewModel(
                    pizzaRepo = pizzaRepo,
                    drinksRepo = drinksRepo,
                    shoppingCart = LadyPizzaApplication.shoppingCart
                )
            }
        }
    )
    val productPricing: ProductPricing = menuViewModel.productPricing(Currency.EURO)
    val menu = menuViewModel.menu.collectAsStateWithLifecycle().value

    val drinks = menuViewModel.drinks.collectAsStateWithLifecycle().value

    Box(modifier = modifier
        .safeContentPadding()
    ) {

        LazyVerticalGrid(columns = GridCells.Fixed(1),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
        ) {
            items(menu) { pizza ->
              CardContainer {
                  PizzaCard(
                      pizza = pizza,
                      navToDetail = navToDetail,
                      productPricing = productPricing
                  )
              }
            }
            items(drinks) { drinkState ->
                CardContainer {
                    if (drinkState.quantity == 0) {
                        DrinkCard(
                            drinkState = drinkState,
                            addDrinkToCart = {
                                menuViewModel.putInCart(drinkState.drink.product)
                            },
                            productPricing = productPricing,

                            )
                    }else
                    {
                        DrinkCardIsInCart(
                            drinkState = drinkState,
                            productPricing = productPricing,
                            addToCart = {
                                menuViewModel.putInCart(drinkState.drink.product)
                            },
                            removeFromCart = {
                                menuViewModel.removeFromCart(drinkState.drink.product)
                            },
                            removeAllFromCart = {
                                menuViewModel.removeAllFromCart(drinkState.drink.product)
                            }
                        )
                    }
                }
            }
        }
    }

}