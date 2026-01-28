package nl.codingwithlinda.ladypizza.core.data

import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.extraCheese
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PizzaFactoryTest {

    val productPricing = EuroProductPricing()
    val dollarPricing = DollarProductPricing(.5)
    lateinit var pizzaFactory: PizzaFactoryUi

    @Before
    fun setUp() {
       pizzaFactory = PizzaFactoryUi()
        pizzaFactory.create()
    }

    @After
    fun tearDown() {
       pizzaFactory.erase()
    }

    @Test
    fun `test create pizza with extra cheese - calculate total costs`(){
        val pizza = pizzaFactory.menu.first()
        val extra = extraCheese
        val pizzaXtra = pizzaFactory.addExtraTopping(pizza, extra)

        val total = pizzaXtra.totalPrice(productPricing)
        assertEquals(9.99, total, 0.0)
    }

    @Test
    fun `test create pizza with extra cheese - calculate total costs in dollars`(){
        val pizza = pizzaFactory.menu.first()
        val extra = extraCheese
        val pizzaXtra = pizzaFactory.addExtraTopping(pizza, extra)

        val total = pizzaXtra.totalPrice(dollarPricing)
        assertEquals(9.99 * .5, total, 0.0)
    }


}