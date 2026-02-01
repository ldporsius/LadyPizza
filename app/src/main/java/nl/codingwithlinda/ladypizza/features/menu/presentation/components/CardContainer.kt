package nl.codingwithlinda.ladypizza.features.menu.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardContainer(
    modifier: Modifier = Modifier.height(200.dp),
    content: @Composable () -> Unit,
    ) {

    ElevatedCard(
        modifier = modifier
    ) {
        content()

    }
}