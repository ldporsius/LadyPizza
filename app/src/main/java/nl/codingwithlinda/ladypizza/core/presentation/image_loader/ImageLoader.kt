package nl.codingwithlinda.ladypizza.core.presentation.image_loader

import android.graphics.Bitmap
import androidx.annotation.DrawableRes

interface ImageLoader
interface RemoteImageLoader : ImageLoader {
    suspend fun load(url: String): Bitmap?
}

interface LocalImageLoader : ImageLoader {
    suspend fun load(@DrawableRes resourceId: Int): Bitmap?
}


