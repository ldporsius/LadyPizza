package nl.codingwithlinda.ladypizza.application.di

import nl.codingwithlinda.ladypizza.core.domain.repo.ExtraToppingsRepository

interface AppModule {

    val extraToppingsRepository: ExtraToppingsRepository
}