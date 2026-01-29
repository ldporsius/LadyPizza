package nl.codingwithlinda.ladypizza.core.presentation.pizza

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.ladypizza.R
import nl.codingwithlinda.ladypizza.core.data.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.data.PizzaFactory
import nl.codingwithlinda.ladypizza.core.data.repo.LocalPriceRepo
import nl.codingwithlinda.ladypizza.core.data.repo.toIngredient
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo
import nl.codingwithlinda.ladypizza.core.presentation.recipes.MyPizza
import nl.codingwithlinda.ladypizza.design.util.UiText

class PizzaFactoryUi(
    private val priceRepo: PriceRepo = LocalPriceRepo()
): PizzaFactory() {

    val menuObservable = MutableStateFlow<List<PizzaUi>>(emptyList())
    private val menu = mutableListOf<PizzaUi>()

    fun erase(){
        menu.clear()
    }

    fun create(pizzaDto: PizzaDto){

        val pizza = pizzaFromDto(pizzaDto)
        println("-- PizzaFactoryUi created pizza from dto: ${pizza?.id()}")
        if(pizza != null){
            menu.add(pizza)
        }
        menuObservable.update {
            menu.toList()
        }

    }

    private fun pizzaFromDto(dto: PizzaDto): PizzaUi?{
        return MyPizza(
                    pizza = Pizza(
                        id = dto.id
                    ).apply {
                        dto.ingredients.onEach {
                            addTopping(it.toIngredient())
                        }
                        setPrice(priceRepo.getPrice(dto.id))
                    },
                    myname = {
                        pizzaNames.getOrElse(dto.id, {UiText.DynamicText(dto.id)})
                    },
                    imageUrl = dto.image_url
                )
            }

}

