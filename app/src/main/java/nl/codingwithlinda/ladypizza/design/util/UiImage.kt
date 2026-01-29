package nl.codingwithlinda.ladypizza.design.util

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import nl.codingwithlinda.ladypizza.core.presentation.image_loader.LocalImageLoader
import nl.codingwithlinda.ladypizza.core.presentation.image_loader.RemoteImageLoader

sealed interface UiImage {
    data class UrlImage(val url: String) : UiImage
    data class ResourceImage(@DrawableRes val resourceId: Int) : UiImage

}

suspend fun UiImage.ResourceImage.toImage(imageLoader: LocalImageLoader): Bitmap?{
  return imageLoader.load(this.resourceId)
}


suspend fun UiImage.UrlImage.toImage(imageLoader: RemoteImageLoader): Bitmap? {
    return imageLoader.load(this.url)
}

@Composable
fun UiImage.UrlImage.ToImage(modifier: Modifier = Modifier) {
    AsyncImage(this.url, contentDescription = null, modifier = modifier)

}