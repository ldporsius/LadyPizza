package nl.codingwithlinda.ladypizza.core.data.pizza.repo.dto

data class ExtraToppingIdDto(
    val ids: List<String> = emptyList()
)

data class ExtraToppingImageDto(
    val id: String = "",
    val image_url: String = ""
)