package nl.codingwithlinda.ladypizza.core.presentation.image_loader

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import coil3.ImageLoader
import coil3.request.ErrorResult
import coil3.request.ImageRequest
import coil3.request.SuccessResult
import coil3.toBitmap
import coil3.util.DebugLogger
import nl.codingwithlinda.ladypizza.R

class CoilImageLoader(
    private val context: Context,
): RemoteImageLoader, LocalImageLoader {

    val loader = ImageLoader(context)
        .newBuilder()
        .logger(DebugLogger())
        .build()
    override suspend fun load(url: String): Bitmap? {
        val request = ImageRequest.Builder(context = context)
            .data(url)
            .build()

           val res = loader.execute(request)
           val drawable = when (res) {
               is ErrorResult -> {
                   println(res.throwable.message)
                   BitmapFactory.decodeResource(
                       context.resources,
                       R.drawable.ic_launcher_foreground
                   )
               }

               is SuccessResult -> {
                   res.image.toBitmap()
               }
           }

           return drawable
       }


    override suspend fun load(resourceId: Int): Bitmap? {
        return BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)
    }


}