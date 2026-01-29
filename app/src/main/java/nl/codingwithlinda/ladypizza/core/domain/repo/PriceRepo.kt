package nl.codingwithlinda.ladypizza.core.domain.repo

interface PriceRepo {
    fun getPrice(id: String): Double
}