package nl.codingwithlinda.ladypizza.core.data.drinks.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import nl.codingwithlinda.ladypizza.core.domain.images.ProductImageBuffer
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.DrinksRepo
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo

class FireStoreDrinksRepo(
    private val priceRepo: PriceRepo
): DrinksRepo {

    val db = Firebase.firestore
    val task = db.collection("drinks")
        .get()
        .addOnFailureListener {
            it.printStackTrace()
        }
        .addOnSuccessListener {
            println("succesfully loaded drinks")
        }

    val taskImageUrls = db.collection("drink_image_urls")
        .get()
        .addOnFailureListener {
            it.printStackTrace()
        }
        .addOnSuccessListener{
            println("loaded drink image urls")
        }

    override suspend fun loadDrinks(): List<Drink> {
        withContext(Dispatchers.IO){
            launch {
                taskImageUrls.await().let {
                    it.documents.map {
                        it.toObject(DrinkImageDto::class.java)
                    }.onEach {
                        it?.let {
                            ProductImageBuffer.saveXref(
                                it.id, it.image_url
                            )
                        }
                    }
                }
            }
        }
        return withContext(Dispatchers.IO){
            val result = task.await().let {
                it.documents.map { snap ->
                    snap.toObject(DrinkDto::class.java)
                }
            }
            val drinks = result.mapNotNull { dto ->
                dto?.drink_ids?.map { id ->
                    Drink(
                        id = id,
                        price = priceRepo.getPrice(id)
                    )
                }
            }
            drinks.flatten()
        }
    }
}
