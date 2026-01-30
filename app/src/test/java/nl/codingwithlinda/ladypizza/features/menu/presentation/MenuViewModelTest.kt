package nl.codingwithlinda.ladypizza.features.menu.presentation

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isNotEmpty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nl.codingwithlinda.ladypizza.core.domain.repo.PizzaRepository
import nl.codingwithlinda.ladypizza.tests.FakePizzaRepo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MenuViewModelTest {

    val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MenuViewModel
    private lateinit var pizzaRepo: PizzaRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        pizzaRepo = FakePizzaRepo()
        viewModel = MenuViewModel(pizzaRepo)

    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `pizzas are added to menu`() = runTest {
       viewModel.menu.test {

           val em0 = awaitItem()
            val em1 = awaitItem()
            assertEquals(2, em1.size)

           val descriptions = em1.map {
               it.description()
           }.flatten()
           assertThat(descriptions).isNotEmpty()

           cancelAndIgnoreRemainingEvents()
        }


    }

}