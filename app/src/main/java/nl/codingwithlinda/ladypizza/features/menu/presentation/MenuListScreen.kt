package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.ladypizza.core.data.drinks.repo.FireStoreDrinksRepo
import nl.codingwithlinda.ladypizza.core.data.drinks.repo.LocalDrinkPriceRepo
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.FirestorePizzaRepository
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.LocalPizzaPriceRepo
import nl.codingwithlinda.ladypizza.core.domain.model.prices.Currency
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.design.util.ToImage
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.asString
import nl.codingwithlinda.ladypizza.features.menu.presentation.components.PizzaCard

@Composable
fun MenuListScreen(
    navToDetail: (String) -> Unit,
    modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val priceRepo = LocalDrinkPriceRepo()
    val pizzaRepo = FirestorePizzaRepository()
    val drinksRepo = FireStoreDrinksRepo(priceRepo)

    val menuViewModel = viewModel<MenuViewModel>(
        factory = viewModelFactory {
            initializer {
                MenuViewModel(
                    pizzaRepo = pizzaRepo,
                    drinksRepo = drinksRepo
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

        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            items(menu) { pizza ->
               PizzaCard(
                   pizza = pizza,
                   navToDetail = navToDetail,
                   productPricing = productPricing
               )
            }
            items(drinks) { drink ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navToDetail(drink.id) }
                ) {
                    with(drink.image()){
                        when(this){
                            is UiImage.UrlImage -> {
                                this.ToImage(
                                    modifier = Modifier.size(200.dp)
                                )
                            }
                            is UiImage.ResourceImage -> {
                                Image(
                                    painter = painterResource(id = this.resourceId),
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    Text(drink.name().asString(context), style = MaterialTheme.typography.titleLarge)

                }
            }
        }
    }

}