package nl.codingwithlinda.ladypizza.core.domain.model.pizza

import nl.codingwithlinda.ladypizza.core.data.PizzaFactory
import nl.codingwithlinda.ladypizza.core.domain.model.pizza.recipes.Margherita
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BasePizzaTest {

    val productPricing = PizzaFactory.productPricing
    val dollarPricing = DollarProductPricing(.5)


    @Before
    fun setUp() {
        PizzaFactory.create()
    }
    @After
    fun tearDown(){
        PizzaFactory.erase()
    }

    @Test
    fun `test margherita pizza - total price is correct`() {
        val pizza = PizzaFactory.menu.first()
        val total = pizza.getPrice(productPricing)
        assertEquals(8.99, total, 0.0)
    }
    @Test
    fun `test margherita pizza - total price is changed at runtime`() {
        val pizza = PizzaFactory.menu.first()
        val total = pizza.getPrice(productPricing)
        assertEquals(8.99, total, 0.0)
        pizza.setPrice(12.99, productPricing)
        val total2 = pizza.getPrice(productPricing)
        assertEquals(12.99, total2, 0.0)
    }
    @Test
    fun `test margherita pizza - total price is changed by dollars`() {
        val pizza = PizzaFactory.menu.first()
        val total = pizza.getPrice(productPricing)
        assertEquals(8.99, total, 0.0)
        pizza.setPrice(10.0, dollarPricing)
        val total2 = pizza.getPrice(productPricing)
        val totalDollars = pizza.getPrice(dollarPricing)
        assertEquals(10.0, total2, 0.0)
        assertEquals(5.0, totalDollars, 0.0)
    }

}