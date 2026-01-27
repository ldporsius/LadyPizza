package nl.codingwithlinda.ladypizza.core.domain.model.pizza.recipes

import nl.codingwithlinda.ladypizza.core.domain.model.ingredients.basil
import nl.codingwithlinda.ladypizza.core.domain.model.ingredients.mozzarellaCheese
import nl.codingwithlinda.ladypizza.core.domain.model.ingredients.oliveOil
import nl.codingwithlinda.ladypizza.core.domain.model.ingredients.tomatoSauce
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza

class Margherita(){
    val id: String = "margherita"
    fun createPizza(): Pizza{
        val pizza = Pizza(id,)
        return pizza.apply {
            addTopping(tomatoSauce)
            addTopping(mozzarellaCheese)
            addTopping(basil)
            addTopping(oliveOil)
        }
    }

}