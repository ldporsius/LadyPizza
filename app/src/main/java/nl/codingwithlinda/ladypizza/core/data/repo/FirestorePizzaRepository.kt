package nl.codingwithlinda.ladypizza.core.data.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository

class FirestorePizzaRepository: PizzaRepository {

    val pizzasRemote = Channel<PizzaDto>()

    val db = Firebase.firestore

    override suspend fun loadPizzas(): List<PizzaDto> {
        return withContext( Dispatchers.IO){
            pizzas.await().let{
                println("--- pizzas in repo: ${it.documents}")

                val dtos = it.documents.mapNotNull { snap ->
                    snap.toObject(PizzaDto::class.java)
                }.onEach {dto ->
                    pizzasRemote.trySend(dto)
                }

                println("dtos finished: $dtos")

                dtos
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