package nl.codingwithlinda.ladypizza.features.menu.presentation.components

import android.R.attr.name
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.drinks.DrinkUi
import nl.codingwithlinda.ladypizza.design.util.ToImage
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.asString
import nl.codingwithlinda.ladypizza.features.menu.presentation.state.DrinkShoppingCartState

@Composable
fun DrinkCard(
    drink: DrinkShoppingCartState,
    productPricing: ProductPricing,
    addDrinkToCart: () -> Unit,
    modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        with(drink.drink.image){
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
            Text(drink.drink.name().asString(context), style = MaterialTheme.typography.titleLarge)
            Text(drink.drink.priceUi(productPricing).asString(context))
            Text("In cart: ${drink.quantity}")
            Button(
                onClick = {
                    addDrinkToCart()
                },
            ) {
                Text("Add to cart")
            }

        }
    }
}