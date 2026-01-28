package nl.codingwithlinda.ladypizza.core.presentation.recipes

import nl.codingwithlinda.ladypizza.core.presentation.ingredients.basil
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.mozzarellaCheese
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.oliveOil
import nl.codingwithlinda.ladypizza.core.presentation.ingredients.tomatoSauce
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza

class Margherita(){
    val id: String = "margherita"
    val pizza = Pizza(id,)
    fun createPizza(): Pizza{

        return pizza.apply {
            addTopping(tomatoSauce)
            addTopping(mozzarellaCheese)
            addTopping(basil)
            addTopping(oliveOil)
        }
    }

    fun description(): String{
        pizza.toppings()
        return "Tomato sauce, mozzarella cheese, basil, olive oil"
    }
}

