package nl.codingwithlinda.ladypizza.core.domain.images

object ProductImageBuffer {

    val lock = Any()
    val productImageXrefs = mutableListOf<ProductImageXref>()

    @Synchronized
    fun saveXref(productId: String, imageUrl: String){
        synchronized(lock) {
            val exists = productImageXrefs.any {
                it.productId == productId && it.imageUrl == imageUrl
            }
            if (exists) return

            productImageXrefs.add(
                ProductImageXref(productId, imageUrl)
            )
        }
    }
    fun getImageByProductId(productId: String): ProductImageXref?{
        return productImageXrefs.firstOrNull {
            it.productId  == productId
        }
    }
}