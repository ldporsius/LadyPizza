package nl.codingwithlinda.ladypizza.features.extra_toppings.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nl.codingwithlinda.ladypizza.design.util.asString

@Composable
fun ExtraToppingsScreen(
    extraToppings: List<ExtraToppingUi>,
    modifier: Modifier = Modifier) {

    LazyColumn(modifier = modifier){
        items(extraToppings){
            Text(it.name().asString())
        }
    }
}