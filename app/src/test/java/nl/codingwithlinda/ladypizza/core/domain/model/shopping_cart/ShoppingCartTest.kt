package nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart

import assertk.assertThat
import assertk.assertions.isEqualTo
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.extraMozzarellaCheese
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi
import org.junit.Before
import org.junit.Test

class ShoppingCartTest {

    lateinit var PizzaFactory: PizzaFactoryUi
    lateinit var pizza: Pizza
    lateinit var shoppingCart: ShoppingCart

    val pricing = EuroProductPricing()
    val extraCheese = extraMozzarellaCheese


    @Before
    fun setup(){

        PizzaFactory = PizzaFactoryUi()
        pizza = Pizza(id = "margherita").apply { setPrice(10.0)}
        shoppingCart = ShoppingCart(pricing)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.buyPizza(myPizza, 2)

        val itemsInCart = shoppingCart.items()

        assertThat(itemsInCart.size).isEqualTo(2)
        assertThat(shoppingCart.total()).isEqualTo(2 * 10.0 + 2 * 1)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind grouped`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.buyPizza(myPizza, 2)

        val itemsInCart = shoppingCart.itemsGrouped()

        assertThat(itemsInCart.size).isEqualTo(1)
        assertThat(shoppingCart.total()).isEqualTo(2 * 10.0 + 2 * 1)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind and one other`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.buyPizza(myPizza, 2)

        val thirdPizza = Pizza(id = "margherita").apply { setPrice(10.0)}
        val thirdPizza2 = PizzaFactory
            .addExtraTopping(thirdPizza, extraCheese)
            .addExtraTopping(extraCheese)


        shoppingCart.buyPizza(thirdPizza2, 1)

        val itemsInCart = shoppingCart.itemsGrouped()

        println("items in cart $itemsInCart")

        itemsInCart.forEach {
            println("--- pizza in cart: $it")
        }

        assertThat(itemsInCart.size).isEqualTo(2)
        assertThat(shoppingCart.total()).isEqualTo(2 * 11.0 + 12)
    }

    @Test
    fun `test shopping cart calculates price in dollars correctly`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.buyPizza(myPizza, 1)

        val itemsInCart = shoppingCart.itemsGrouped()

        itemsInCart.forEach {
            println("--- pizza in cart: $it")
        }

        assertThat(itemsInCart.size).isEqualTo(1)
        shoppingCart.pricing = DollarProductPricing(.5)
        assertThat(shoppingCart.total()).isEqualTo(0.5 * 11.0 )

        shoppingCart.pricing = pricing
        assertThat(shoppingCart.total()).isEqualTo( 11.0 )
    }

    @Test
    fun `test adding a drink to shopping cart`(){
        val drink = Drink(
            id = "mineral_water",
            price = 1.0
        )
        shoppingCart.buyDrink(drink)
        assertThat(shoppingCart.total()).isEqualTo( 1.0 )
    }
}