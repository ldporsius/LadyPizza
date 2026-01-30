package nl.codingwithlinda.ladypizza.features.menu.presentation.components

import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CardContainer(
    content: @Composable () -> Unit,
    ) {

    ElevatedCard() {
        content()

    }
}