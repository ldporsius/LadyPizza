package nl.codingwithlinda.ladypizza.core.data

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.PizzaDto
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.toDomain
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.extraMozzarellaCheese
import nl.codingwithlinda.ladypizza.core.domain.model.prices.DollarProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaFactoryUi
import nl.codingwithlinda.ladypizza.core.presentation.pizza.PizzaUi
import nl.codingwithlinda.ladypizza.core.presentation.pizza.sorting.PizzaSortOrder
import nl.codingwithlinda.ladypizza.core.presentation.pizza.sorting.SortPizzaOpinionated
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class PizzaFactoryTest {

    private val euroProductPricing = EuroProductPricing()
    private val dollarPricing = DollarProductPricing(.5)
    private lateinit var pizzaFactory: PizzaFactoryUi


    private val dtos = listOf(
        PizzaDto(id = "pepperoni", ingredients = listOf("mozzarella_cheese", "pepperoni")),
        PizzaDto(id = "margherita", ingredients = listOf("mozzarella_cheese", "tomato_sauce")),
        PizzaDto(id = "bbq_chicken", ingredients = listOf("mozzarella_cheese", "bbq_sauce")),
    )


    @Before
    fun setUp() {
       pizzaFactory = PizzaFactoryUi()
        runBlocking {
            dtos.map {
                it.toDomain()
            }
                .onEach {
                pizzaFactory.createUiPizza(it)
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
        val basicPrice = pizzaUi.pizza.getPrice(euroProductPricing)
        val extra = extraMozzarellaCheese
        val pizzaXtra = pizzaFactory.addExtraTopping(pizzaUi.pizza, extra)

        val total = pizzaXtra.totalPrice(euroProductPricing)
        assertEquals(basicPrice + extra.price, total, 0.0)
    }

    @Test
    fun `test create pizza with extra cheese - calculate total costs in dollars`() = runBlocking{
        val pizzaUi = pizzaFactory.menuObservable.first().first()
        val basicPrice = pizzaUi.pizza.getPrice(euroProductPricing)
        val extra = extraMozzarellaCheese
        val pizzaXtra = pizzaFactory.addExtraTopping(pizzaUi.pizza, extra)

        val total = pizzaXtra.totalPrice(dollarPricing)
        assertEquals((basicPrice + extra.price) * .5, total, 0.0)
    }

    @Test
    fun `test pizza list is sorted as intended`() = runTest {

        val pizzaUi = pizzaFactory.menuObservable.first()
        val sortingAlghorithm : PizzaSortOrder<PizzaUi> = SortPizzaOpinionated(pizzaUi)
        val sortedPizzas = sortingAlghorithm.sortBy()
        assertEquals("margherita", sortedPizzas.first().id())
        assertEquals("pepperoni", sortedPizzas[1].id())
        assertEquals("bbq_chicken", sortedPizzas[2].id())

    }


}