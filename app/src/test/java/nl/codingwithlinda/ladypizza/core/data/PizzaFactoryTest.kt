package nl.codingwithlinda.ladypizza.core.data

import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.extraCheese
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PizzaFactoryTest {

    val productPricing = PizzaFactory.productPricing
    val dollarPricing = DollarProductPricing(.5)

    @Before
    fun setUp() {
        PizzaFactory.create()
    }

    @After
    fun tearDown() {
        PizzaFactory.create()
    }

    @Test
    fun `test create pizza with extra cheese - calculate total costs`(){
        val pizza = PizzaFactory.menu.first()
        val extra = extraCheese
        val pizzaXtra = PizzaFactory.addExtraTopping(pizza, extra)

        val total = pizzaXtra.totalPrice(productPricing)
        assertEquals(9.99, total, 0.0)
    }

    @Test
    fun `test create pizza with extra cheese - calculate total costs in dollars`(){
        val pizza = PizzaFactory.menu.first()
        val extra = extraCheese
        val pizzaXtra = PizzaFactory.addExtraTopping(pizza, extra)

        val total = pizzaXtra.totalPrice(dollarPricing)
        assertEquals(9.99 * .5, total, 0.0)
    }


}