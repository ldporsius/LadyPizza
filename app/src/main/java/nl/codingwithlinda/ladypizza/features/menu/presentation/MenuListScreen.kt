package nl.codingwithlinda.ladypizza.features.menu.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi
import nl.codingwithlinda.ladypizza.design.util.asString
import nl.codingwithlinda.ladypizza.design.util.toImage

@Composable
fun MenuListScreen(
    navToDetail: (String) -> Unit,
    modifier: Modifier = Modifier) {


    val context = LocalContext.current

    val menuViewModel = viewModel<MenuViewModel>(
        factory = viewModelFactory {
            initializer {
                MenuViewModel(PizzaFactoryUi())
            }
        }
    )
    Box(modifier = modifier
        .safeContentPadding()
    ) {

        LazyVerticalGrid(columns = GridCells.Fixed(1)) {
            items(menuViewModel.menu()) { pizza ->
                Row(
                    modifier = Modifier.clickable { navToDetail(pizza.id) }
                ) {
                    Image(
                        bitmap = pizza.image.toImage(context).toBitmap(200, 200).asImageBitmap(),
                        contentDescription = null
                    )
                    Text(pizza.description().joinToString {
                        it.asString(context)
                    })
                }
            }
        }
    }

}