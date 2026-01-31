package nl.codingwithlinda.ladypizza.core.data.pizza.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.dto.ExtraToppingIdDto
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.repo.ExtraToppingsRepository
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo

class FireStoreExtraToppingsRepository(
  private val priceRepo: PriceRepo
): ExtraToppingsRepository {

    val db = Firebase.firestore

    val task = db.collection("extra_toppings")
        .document("toppings")
        .get()
        .addOnSuccessListener {
            println("successfully loaded extra toppings")
        }
        .addOnFailureListener {
            it.printStackTrace()
        }
    override suspend fun loadExtraToppings(): List<ExtraTopping> {

        return withContext(Dispatchers.IO){
            val dto = task.await()
                .toObject(ExtraToppingIdDto::class.java)

            val toppings = dto?.let { dto ->
                println("loaded extra toppings: $dto")
                dto.ids.map {
                    ExtraTopping(
                        id = it,
                        price = priceRepo.getPrice(it)
                    )
                }
            }
            toppings ?: emptyList()
        }
    }
}