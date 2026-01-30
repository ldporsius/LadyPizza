package nl.codingwithlinda.ladypizza.core.data.drinks.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
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

    override suspend fun loadDrinks(): List<Drink> {
        return withContext(Dispatchers.IO){
            val result = task.await().let {
                it.documents.map { snap ->
                    snap.toObject(DrinkDto::class.java)
                }
            }
            //side effect: save image urls in object
            result.onEach {dto ->
                dto?.run {
                    ProductImageBuffer.saveXref(this.id, this.image_url)
                }
            }
            val drinks = result.mapNotNull { dto ->
                if (dto == null) null
                else Drink(
                    id = dto.id,
                    price = priceRepo.getPrice(dto.id)
                )
            }
            drinks
        }
    }
}
