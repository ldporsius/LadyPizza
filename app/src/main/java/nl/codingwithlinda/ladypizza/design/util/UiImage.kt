package nl.codingwithlinda.ladypizza.design.util

import androidx.annotation.DrawableRes

sealed interface UiImage {
    data class UrlImage(val url: String) : UiImage
    data class ResourceImage(@DrawableRes val resourceId: Int) : UiImage

}