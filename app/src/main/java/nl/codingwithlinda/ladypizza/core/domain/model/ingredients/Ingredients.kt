package nl.codingwithlinda.ladypizza.core.domain.model.ingredients

import nl.codingwithlinda.ladypizza.core.domain.model.Product


val tomatoSauce = TomatoSauce(
    id = "tomatoSauce",

)
val mozzarellaCheese = MozzarellaCheese(
    id = "mozzarellaCheese",

)

val basil = object : Product{
    override val id: String
        get() = "basil"

}
val oliveOil = object : Product{
    override val id: String
        get() = "oliveOil"

}
