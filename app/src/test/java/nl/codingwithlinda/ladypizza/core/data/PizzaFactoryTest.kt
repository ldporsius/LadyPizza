package nl.codingwithlinda.ladypizza.core.data

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import nl.codingwithlinda.ladypizza.core.data.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.extraMozzarellaCheese
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

    val dtos = listOf(
        PizzaDto(id = "margherita", ingredients = listOf("mozzarellaCheese", "tomatoSauce"))
    )


    @Before
    fun setUp() {
       pizzaFactory = PizzaFactoryUi()
        runBlocking {
            dtos.onEach {
                pizzaFactory.create(it)
            }
        }
    }

    @After
    fun tearDown() {
       pizzaFactory.erase()
    }

    @Test
    fun `test create pizza with extra cheese - calculate total costs`() = runBlocking{
        val pizzaUi = pizzaFactory.menuObservable.first().first()
        val extra = extraMozzarellaCheese
        val pizzaXtra = pizzaFactory.addExtraTopping(pizzaUi.pizza, extra)

        val total = pizzaXtra.totalPrice(productPricing)
        assertEquals(9.99, total, 0.0)
    }

    @Test
    fun `test create pizza with extra cheese - calculate total costs in dollars`() = runBlocking{
        val pizzaUi = pizzaFactory.menuObservable.first().first()
        val extra = extraMozzarellaCheese
        val pizzaXtra = pizzaFactory.addExtraTopping(pizzaUi.pizza, extra)

        val total = pizzaXtra.totalPrice(dollarPricing)
        assertEquals(9.99 * .5, total, 0.0)
    }


}