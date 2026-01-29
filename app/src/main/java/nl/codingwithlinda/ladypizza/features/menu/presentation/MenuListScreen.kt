package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.ladypizza.core.data.repo.FirestorePizzaRepository
import nl.codingwithlinda.ladypizza.core.domain.model.prices.Currency
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi
import nl.codingwithlinda.ladypizza.design.util.ToImage
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.asString
import nl.codingwithlinda.ladypizza.design.util.toImage

@Composable
fun MenuListScreen(
    navToDetail: (String) -> Unit,
    modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val pizzaRepo = FirestorePizzaRepository()

    val menuViewModel = viewModel<MenuViewModel>(
        factory = viewModelFactory {
            initializer {
                MenuViewModel(pizzaRepo = pizzaRepo)
            }
        }
    )
    val productPricing: ProductPricing = menuViewModel.productPricing(Currency.EURO)
    val menu = menuViewModel.menu.collectAsStateWithLifecycle().value

    Box(modifier = modifier
        .safeContentPadding()
    ) {

        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            items(menu) { pizza ->
                Row(
                    modifier = Modifier.clickable { navToDetail(pizza.id()) }
                ) {
                    with(pizza.image){
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
                    Column {
                        Text(pizza.name().asString(context), style = MaterialTheme.typography.titleLarge)
                        Text(pizza.description().joinToString {
                            it.asString(context)
                        })
                        Text(pizza.price(productPricing).asString(context))
                    }
                }
            }
        }
    }

}