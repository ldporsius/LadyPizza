package nl.codingwithlinda.ladypizza.core.domain.model.pizza

import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.pizza.MyPizza
import nl.codingwithlinda.ladypizza.design.util.UiText
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BasePizzaTest {

    val euroPricing = EuroProductPricing()
    val dollarPricing = DollarProductPricing(.5)

    lateinit var pizza: Pizza
    @Before
    fun setUp() {
       pizza =  Pizza(id = "margherita").apply { setPrice(8.99,) }

    }
    @After
    fun tearDown(){
       
    }

    @Test
    fun `test margherita pizza - total price is correct`() {
        
        val total = pizza.getPrice(euroPricing)
        assertEquals(8.99, total, 0.0)
    }
    @Test
    fun `test margherita pizza - total price is changed at runtime`() {
        
        val total = pizza.getPrice(euroPricing)
        assertEquals(8.99, total, 0.0)
        pizza.setPrice(12.99,)
        val total2 = pizza.getPrice(euroPricing)
        assertEquals(12.99, total2, 0.0)
    }
    @Test
    fun `test margherita pizza - total price is changed by dollars`() {
       
        val total = pizza.getPrice(euroPricing)
        assertEquals(8.99, total, 0.0)
        pizza.setPrice(10.0, )
        val total2 = pizza.getPrice(euroPricing)
        val totalDollars = pizza.getPrice(dollarPricing)
        assertEquals(10.0, total2, 0.0)
        assertEquals(5.0, totalDollars, 0.0)
    }

}