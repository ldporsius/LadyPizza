package nl.codingwithlinda.ladypizza.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import nl.codingwithlinda.ladypizza.application.LadyPizzaApplication
import nl.codingwithlinda.ladypizza.features.menu.presentation.MenuListScreen
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailRoot
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailScreen

@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {

    val backStack = rememberNavBackStack(Menu)

    val extraToppingsViewModel = LadyPizzaApplication.appModule.extraToppingsViewModel

    NavDisplay(
        backStack = backStack,
        entryProvider = {key ->
            NavEntry(key){
                when(key){
                    is Menu -> MenuListScreen(
                        navToDetail = {id -> backStack.add(ProductDetail(id))}
                    )
                    is ProductDetail -> ProductDetailRoot(
                        key.id,
                        extraToppingsViewModel = extraToppingsViewModel,
                        navBack = {backStack.retainAll(listOf(Menu))}
                    )
                }
            }
        }
    )
}