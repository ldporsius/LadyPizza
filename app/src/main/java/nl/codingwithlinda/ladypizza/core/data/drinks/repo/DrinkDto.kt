package nl.codingwithlinda.ladypizza.core.data.drinks.repo

data class DrinkDto(
    val drink_ids: List<String> = emptyList(),
)

data class DrinkImageDto(
    val id: String = "",
    val image_url: String = ""
)