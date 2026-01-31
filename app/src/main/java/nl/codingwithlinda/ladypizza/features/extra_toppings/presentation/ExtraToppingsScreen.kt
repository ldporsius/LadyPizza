package nl.codingwithlinda.ladypizza.features.extra_toppings.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nl.codingwithlinda.ladypizza.core.domain.model.ProductWithPricing
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.design.util.ToImage
import nl.codingwithlinda.ladypizza.design.util.asString

@Composable
fun ExtraToppingsScreen(
    extraToppings: List<ExtraToppingUi>,
    buyExtraTopping: (ProductWithPricing) -> Unit,
    modifier: Modifier = Modifier) {

    LazyVerticalGrid (
        columns = GridCells.Fixed(3),
        modifier = modifier){

        items(extraToppings){
            Column(
                modifier = Modifier
                    .size(200.dp)
                    .clickable{
                        buyExtraTopping(it.product)
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                it.image.ToImage(
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(1f)
                )
                Text(it.name().asString())
            }
        }
    }
}