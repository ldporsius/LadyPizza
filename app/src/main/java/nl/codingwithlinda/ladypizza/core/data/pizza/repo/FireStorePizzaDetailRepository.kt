package nl.codingwithlinda.ladypizza.core.data.pizza.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.dto.PizzaDto
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.dto.toDomain
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaDetailRepository

class FireStorePizzaDetailRepository: PizzaDetailRepository {

    val db = Firebase.firestore
    override suspend fun loadPizza(id: String): Pizza {
       return withContext(Dispatchers.IO) {
            val task = db.collection("pizza_recipes").document(id)
                .get()
                .addOnSuccessListener {
                    println("got pizza: $id")
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }

            val result = task.await()
            val pizza = with(result) {
                this.toObject(PizzaDto::class.java)
            }?.toDomain()

            return@withContext pizza ?: error("Could not find pizza with id: $id")
        }
    }
}