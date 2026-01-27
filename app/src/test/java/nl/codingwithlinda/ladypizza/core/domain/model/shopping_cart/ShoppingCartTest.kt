package nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart

import assertk.assertThat
import assertk.assertions.isEqualTo
import nl.codingwithlinda.ladypizza.core.data.PizzaFactory
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.extraCheese
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.Pizza
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import org.junit.Before
import org.junit.Test

class ShoppingCartTest {

    lateinit var pizza: Pizza
    lateinit var shoppingCart: ShoppingCart

    val pricing = EuroProductPricing()

    @Before
    fun setup(){
        PizzaFactory.create()
        pizza = PizzaFactory.menu.first()
        shoppingCart = ShoppingCart(pricing)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.buyPizza(myPizza, 2)

        val itemsInCart = shoppingCart.items()

        println("items in cart $itemsInCart")

        itemsInCart.forEach {
            println("--- pizza in cart: $it")
        }

        assertThat(itemsInCart.size).isEqualTo(2)
        assertThat(shoppingCart.total()).isEqualTo(2 * 9.99)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind grouped`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.buyPizza(myPizza, 2)

        val itemsInCart = shoppingCart.itemsGrouped()

        println("items in cart $itemsInCart")

        itemsInCart.forEach {
            println("--- pizza in cart: $it")
        }

        assertThat(itemsInCart.size).isEqualTo(1)
        assertThat(shoppingCart.total()).isEqualTo(2 * 9.99)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind and one other`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.buyPizza(myPizza, 2)

        val thirdPizza = PizzaFactory.menu.first()
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
        assertThat(shoppingCart.total()).isEqualTo(2 * 9.99 + 10.99)
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
        assertThat(shoppingCart.total()).isEqualTo(0.5 * 9.99 )

        shoppingCart.pricing = pricing
        assertThat(shoppingCart.total()).isEqualTo( 9.99 )
    }
}