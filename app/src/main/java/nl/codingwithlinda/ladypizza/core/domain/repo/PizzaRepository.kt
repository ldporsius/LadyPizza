package nl.codingwithlinda.ladypizza.core.domain.repo

import nl.codingwithlinda.ladypizza.core.data.repo.PizzaDto

interface PizzaRepository {

    suspend fun loadPizzas(): List<PizzaDto>
}