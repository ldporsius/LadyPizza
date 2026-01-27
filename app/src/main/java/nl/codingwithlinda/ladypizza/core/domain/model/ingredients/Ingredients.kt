package nl.codingwithlinda.ladypizza.core.domain.model.ingredients

import nl.codingwithlinda.ladypizza.core.domain.model.Product


val tomatoSauce = TomatoSauce(
    id = "tomatoSauce",
    name = "Tomato Sauce",
)
val mozzarellaCheese = MozzarellaCheese(
    id = "mozzarellaCheese",
    name = "Mozzarella Cheese",
)

val basil = object : Product{
    override val id: String
        get() = "basil"
    override val name: String
        get() = "Basil"
}
val oliveOil = object : Product{
    override val id: String
        get() = "oliveOil"
    override val name: String
        get() = "Olive oil"
}
