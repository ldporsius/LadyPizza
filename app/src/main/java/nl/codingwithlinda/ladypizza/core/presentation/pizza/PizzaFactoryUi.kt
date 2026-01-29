package nl.codingwithlinda.ladypizza.core.presentation.pizza

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.core.data.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.data.PizzaFactory
import nl.codingwithlinda.ladypizza.core.data.repo.toIngredient
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.presentation.recipes.MyPizza
import nl.codingwithlinda.ladypizza.design.util.UiText

class PizzaFactoryUi(
): PizzaFactory() {

    val menuObservable = MutableStateFlow<List<PizzaUi>>(emptyList())
    private val menu = mutableListOf<PizzaUi>()

    fun erase(){
        menu.clear()
    }

    suspend fun create(pizzaDto: PizzaDto){

        val pizza = pizzaFromDto(pizzaDto)
        println("-- PizzaFactoryUi created pizza from dto: ${pizza?.id()}")
        if(pizza != null){
            menu.add(pizza)
        }
        menuObservable.update {
            menu.toList()
        }

    }

    suspend fun pizzaFromDto(dto: PizzaDto): PizzaUi?{
        return when(dto.id){
            "margherita" -> {
                val margheritaUrl = dto.image_url
                MyPizza(
                    pizza = Pizza(
                        id = dto.id
                    ).apply {
                        dto.ingredients.onEach {
                            addTopping(it.toIngredient())
                        }
                        setPrice(8.99)
                    },
                    myname = {
                        UiText.DynamicText("Margherita")
                    },
                    imageUrl = margheritaUrl
                )

            }
            "bbq_chicken" -> {
                val bbqChickenUrl = dto.image_url
                MyPizza(
                    pizza = Pizza(
                        id = dto.id
                    ).apply {
                        dto.ingredients.onEach {
                            addTopping(it.toIngredient())
                        }
                        setPrice(10.99)
                    },
                    myname = {
                        UiText.StringResourceText(R.string.bbq_chicken)
                    },
                    imageUrl = bbqChickenUrl
                )
            }
            else -> null
        }
    }
}

