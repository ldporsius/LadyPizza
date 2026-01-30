package nl.codingwithlinda.ladypizza.core.data.pizza.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import nl.codingwithlinda.ladypizza.core.domain.images.ProductImageBuffer
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository

class FirestorePizzaRepository: PizzaRepository {


    val db = Firebase.firestore

    override suspend fun loadPizzas(): List<Pizza> {
        return withContext( Dispatchers.IO){
            pizzas.await().let{
                println("--- pizzas in repo: ${it.documents}")

                val dtos = it.documents.mapNotNull { snap ->
                    snap.toObject(PizzaDto::class.java)
                }.onEach {dto ->
                    ProductImageBuffer.saveXref(dto.id, dto.image_url)
                }

                println("dtos finished: $dtos")

                dtos.map {
                    it.toDomain()
                }
            }
        }
    }
    private val pizzas = db.collection("pizza_recipes")
        .get()
        .addOnSuccessListener {
           println("success")
        }
        .addOnFailureListener {
            println("error: ${it.message}")
        }


}