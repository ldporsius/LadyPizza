package nl.codingwithlinda.ladypizza.features.product_detail.presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nl.codingwithlinda.ladypizza.core.domain.model.extra_toppings.ExtraTopping
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailViewModel.Companion.KEY_PIZZA_ID
import nl.codingwithlinda.ladypizza.tests.FakePizzaDetailRepo
import nl.codingwithlinda.ladypizza.tests.FakePizzaRepo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductDetailViewModelTest {

    val testDispatcher = StandardTestDispatcher()
    val savedStateHandle = SavedStateHandle(
        mapOf(KEY_PIZZA_ID to "margherita")
    )
    val viewmodel = ProductDetailViewModel(
        savedStateHandle = savedStateHandle,
        detailRepository = FakePizzaDetailRepo()
    )

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }
    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `viewmodel updates mPizza after adding extra topping`() = runTest {
        viewmodel.mPizza.test {
            awaitItem()
            val em1 = awaitItem()
            assertThat(em1?.id()).isEqualTo("margherita")

            viewmodel.buyExtraTopping(
                ExtraTopping(
                    "cheese", 1.0
                )
            )

            val em2 = awaitItem()
            assertThat(em2?.pizza?.extraToppingsUsed()).isNotNull().hasSize(1)
        }
    }
}