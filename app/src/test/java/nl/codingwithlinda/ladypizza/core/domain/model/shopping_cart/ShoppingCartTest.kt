package nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotSameInstanceAs
import assertk.assertions.isSameInstanceAs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.Drink
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraToppingsPriceRepo
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
    val extraCheese = ExtraTopping("cheese", 1.0)


    @Before
    fun setup(){

        PizzaFactory = PizzaFactoryUi()
        pizza = Pizza(id = "margherita").apply { setPrice(10.0)}
        shoppingCart = ShoppingCart(pricing)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.putInCart(myPizza,)
        shoppingCart.putInCart(myPizza,)

        val itemsInCart = shoppingCart.items()

        assertThat(itemsInCart.size).isEqualTo(2)
        assertThat(shoppingCart.total()).isEqualTo(2 * 10.0 + 2 * 1)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind grouped`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.putInCart(myPizza, )
        shoppingCart.putInCart(myPizza, )

        val itemsInCart = shoppingCart.itemsGrouped()

        assertThat(itemsInCart.size).isEqualTo(1)
        assertThat(shoppingCart.total()).isEqualTo(2 * 10.0 + 2 * 1)
    }

    @Test
    fun `test shopping cart has two pizzas of the same kind and one other`(){
        val myPizza = PizzaFactory.addExtraTopping(pizza, extraCheese)
        shoppingCart.putInCart(myPizza,)
        shoppingCart.putInCart(myPizza,)

        val thirdPizza = Pizza(id = "margherita").apply { setPrice(10.0)}
        val thirdPizza2 = PizzaFactory
            .addExtraTopping(thirdPizza, extraCheese)
            .addExtraTopping(extraCheese)


        shoppingCart.putInCart(thirdPizza2,)

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
        shoppingCart.putInCart(myPizza, )

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
        shoppingCart.putInCart(drink)
        assertThat(shoppingCart.total()).isEqualTo( 1.0 )
    }

    @Test
    fun `test remove a drink from shopping cart`(){
        val drink = Drink(
            id = "mineral_water",
            price = 1.0
        )
        shoppingCart.putInCart(drink)
        assertThat(shoppingCart.total()).isEqualTo( 1.0 )

        shoppingCart.removeFromCart(drink)
        assertThat(shoppingCart.items()).isEmpty()
    }

    @Test
    fun `test remove all drinks of one kind from shopping cart`(){
        val drink = Drink(
            id = "mineral_water",
            price = 1.0
        )
        repeat(2) {
            shoppingCart.putInCart(drink)
        }
       assertThat(shoppingCart.items().size).isEqualTo(2)

        shoppingCart.removeAllFromCart(drink)
        assertThat(shoppingCart.items()).isEmpty()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test shopping cart is observable`() = runTest {
        shoppingCart.cartObservable.test {
            val em0 = awaitItem()
            println("shopping cart em0: $em0")
            assertThat(em0).isEmpty()

            repeat(2) { r ->
                shoppingCart.putInCart(
                    Drink(
                        id = "mineral_water",
                        price = 1.0
                    )
                )
                assertThat(shoppingCart.items()).hasSize(1 + r)

                val em1 = awaitItem()
                assertThat(em1).hasSize(1 + r)
            }
        }
    }
}