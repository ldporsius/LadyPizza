package nl.codingwithlinda.ladypizza.features.menu.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nl.codingwithlinda.ladypizza.core.data.drinks.repo.LocalDrinkPriceRepo
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.LocalPizzaPriceRepo
import nl.codingwithlinda.ladypizza.core.domain.model.drinks.DrinksRepo
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository
import nl.codingwithlinda.ladypizza.tests.FakeDrinksRepo
import nl.codingwithlinda.ladypizza.tests.FakePizzaRepo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MenuViewModelTest {

    val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MenuViewModel

    private val priceRepo = LocalDrinkPriceRepo()
    private lateinit var pizzaRepo: PizzaRepository
    private lateinit var drinksRepo: DrinksRepo

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        pizzaRepo = FakePizzaRepo()
        drinksRepo = FakeDrinksRepo(priceRepo)
        viewModel = MenuViewModel(pizzaRepo, drinksRepo)

    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `pizzas are added to menu`() = runTest {
       viewModel.menu.test {
           awaitItem()
            val em1 = awaitItem()
            assertEquals(2, em1.size)

           val descriptions = em1.map {
               it.description()
           }.flatten()
           assertThat(descriptions).isNotEmpty()

           cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `drinks are added to menu`() = runTest{
        viewModel.drinks.test {

            val em1 = awaitItem()
            assertThat(em1).hasSize(1)
            val price = em1.first().price
            assertThat(price).isEqualTo(1.0)
        }
    }

}