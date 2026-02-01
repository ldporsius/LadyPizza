package nl.codingwithlinda.ladypizza.features.menu.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.prices.formatLocale
import nl.codingwithlinda.ladypizza.design.util.ToImage
import nl.codingwithlinda.ladypizza.design.util.asString
import nl.codingwithlinda.ladypizza.features.menu.presentation.state.DrinkShoppingCartState

@Composable
fun DrinkCardIsInCart(
    drinkState: DrinkShoppingCartState,
    productPricing: ProductPricing,
    removeFromCart: () -> Unit,
    removeAllFromCart: ()-> Unit,
    addToCart: ()-> Unit,
    modifier: Modifier = Modifier) {

    Row(
        modifier = modifier
    ) {
        drinkState.drink.image.ToImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(drinkState.drink.name().asString())
                IconButton(
                    onClick = {
                        removeAllFromCart()
                    }
                ) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(
                    onClick = {
                        removeFromCart()
                    }
                ) {
                    Text("-")
                }
                Text("${drinkState.quantity}")

                IconButton(
                    onClick = {
                        addToCart()
                    }
                ) {
                    Text("+")
                }

                DrinkTotalPriceComponent(
                    drinkState = drinkState,
                    productPricing = productPricing
                )
            }


        }

    }
}

@Composable
fun DrinkTotalPriceComponent(
    drinkState: DrinkShoppingCartState,
    productPricing: ProductPricing,
    modifier: Modifier = Modifier) {

    Column {
        val total = drinkState.drink.product.price(productPricing) * drinkState.quantity
        Text(productPricing.formatLocale(price = total),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
        drinkState.quantity.toString() + "x" + drinkState.drink.priceUi(productPricing).asString()
        )
    }
}