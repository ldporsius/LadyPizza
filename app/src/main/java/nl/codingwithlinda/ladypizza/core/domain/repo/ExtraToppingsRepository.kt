package nl.codingwithlinda.ladypizza.core.domain.repo

import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping

interface ExtraToppingsRepository {
    suspend fun loadExtraToppings(): List<ExtraTopping>
}