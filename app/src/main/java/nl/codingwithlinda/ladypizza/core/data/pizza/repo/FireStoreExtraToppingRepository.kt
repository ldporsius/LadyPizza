package nl.codingwithlinda.ladypizza.core.data.pizza.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.dto.ExtraToppingIdDto
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.repo.ExtraToppingsRepository

class FireStoreExtraToppingsRepository: ExtraToppingsRepository {

    val db = Firebase.firestore

    val task = db.collection("extra_toppings")
        .document("toppings")
        .get()
        .addOnSuccessListener {
            println("success")
        }
        .addOnFailureListener {
            it.printStackTrace()
        }
    override suspend fun loadExtraToppings(): List<ExtraTopping> {

        return withContext(Dispatchers.IO){
            val dto = task.await()
                .toObject(ExtraToppingIdDto::class.java)

            val toppings = dto?.let {
                it.ids.map {
                    ExtraTopping(
                        id = it,
                        price = 0.0
                    )
                }
            }
            toppings ?: emptyList()
        }
    }
}