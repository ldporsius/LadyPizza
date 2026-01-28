package nl.codingwithlinda.ladypizza.features.product_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProductDetailScreen(
    key: String,
    navBack: () -> Unit,
    modifier: Modifier = Modifier) {
    Box(modifier = modifier.safeContentPadding()) {
        Column {
            IconButton(onClick = navBack) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
            Text("Product detail $key")
        }
    }
}