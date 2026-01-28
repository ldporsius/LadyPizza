package nl.codingwithlinda.ladypizza.design.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

sealed interface UiImage {
    data class UrlImage(val url: String) : UiImage
    data class ResourceImage(@DrawableRes val resourceId: Int) : UiImage

}

fun UiImage.toImage(context: Context): Drawable{
    when(this){
        is UiImage.UrlImage -> error("not implemented")
        is UiImage.ResourceImage -> {
            return context.resources.getDrawable(resourceId, null)
        }

    }
}