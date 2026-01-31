package nl.codingwithlinda.ladypizza.core.data.pizza.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.dto.ExtraToppingIdDto
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.dto.ExtraToppingImageDto
import nl.codingwithlinda.ladypizza.core.domain.images.ProductImageBuffer
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.repo.ExtraToppingsRepository
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo

class FireStoreExtraToppingsRepository(
  private val priceRepo: PriceRepo
): ExtraToppingsRepository {

    val db = Firebase.firestore

    val taskIds = db.collection("extra_toppings")
        .document("toppings")
        .get()
        .addOnSuccessListener {
            println("successfully loaded extra toppings")
        }
        .addOnFailureListener {
            it.printStackTrace()
        }
    val taskImageUrls = db.collection("extra_toppings")
        .document("toppin_image_urls")
        .get()
        .addOnSuccessListener {
            println("successfully loaded extra toppings image urls")
        }
        .addOnFailureListener {
            it.printStackTrace()
        }
    override suspend fun loadExtraToppings(): List<ExtraTopping> {


        return withContext(Dispatchers.IO){
            val dto = taskIds.await()
                .toObject(ExtraToppingIdDto::class.java)

            dto?.ids?.onEach {id->
                val image = db.collection("extra_toppings")
                    .document("toppin_image_urls")
                    .get()
                    .await()
                    .data?.onEach { (key, value) ->
                        ProductImageBuffer.saveXref(key + "_extra", value.toString())
                    }



            }
            val toppings = dto?.let { dto ->
                println("loaded extra toppings: $dto")
                dto.ids.map {
                    ExtraTopping(
                        id = it + "_extra",
                        price = priceRepo.getPrice(it)
                    )
                }
            }
            toppings ?: emptyList()
        }
    }
}