package nl.codingwithlinda.ladypizza.features.menu.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.ladypizza.core.domain.model.prices.ProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaUi
import nl.codingwithlinda.ladypizza.design.util.ToImage
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.asString

@Composable
fun PizzaCard(
    pizza: PizzaUi,
    navToDetail: (String) -> Unit,
    productPricing: ProductPricing,
    modifier: Modifier = Modifier) {

    val context = LocalContext.current
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