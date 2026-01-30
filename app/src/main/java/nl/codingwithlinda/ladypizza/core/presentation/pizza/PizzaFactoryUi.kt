package nl.codingwithlinda.ladypizza.core.presentation.pizza

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.data.pizza.PizzaFactory
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.LocalPriceRepo
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.toIngredient
import nl.codingwithlinda.ladypizza.core.domain.images.ProductImageBuffer
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.repo.PriceRepo
import nl.codingwithlinda.ladypizza.design.util.UiText

class PizzaFactoryUi(
    private val priceRepo: PriceRepo = LocalPriceRepo()
): PizzaFactory() {

    val menuObservable = MutableStateFlow<List<PizzaUi>>(emptyList())
    private val menu = mutableListOf<PizzaUi>()

    fun erase(){
        menu.clear()
    }

    fun createUiPizza(pizza: Pizza){
       pizzaUi(pizza)?.let {
            menu.add(it)
            menuObservable.update {
                menu.toList()
            }
        }
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

    private fun pizzaUi(pizza: Pizza): PizzaUi?{
        return MyPizza(
            pizza = Pizza(
                id = pizza.id
            ).apply {
                setPrice(priceRepo.getPrice(pizza.id))
            },
            myname = {
                pizzaNames.getOrElse(pizza.id, {UiText.DynamicText(pizza.id)})
            },
            imageUrl = ProductImageBuffer.getImageByProductId(pizza.id)?.imageUrl ?: ""
        )
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

