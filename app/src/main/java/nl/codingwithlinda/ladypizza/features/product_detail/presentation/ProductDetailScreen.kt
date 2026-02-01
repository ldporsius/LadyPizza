package nl.codingwithlinda.ladypizza.features.product_detail.presentation

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.util.fastJoinToString
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nl.codingwithlinda.ladypizza.core.data.pizza.repo.FireStorePizzaDetailRepository
import nl.codingwithlinda.ladypizza.design.util.ToImage
import nl.codingwithlinda.ladypizza.design.util.UiImage
import nl.codingwithlinda.ladypizza.design.util.asString
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailViewModel
import nl.codingwithlinda.ladypizza.features.product_detail.presentation.ProductDetailViewModel.Companion.KEY_PIZZA_ID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    detailViewmodel: ProductDetailViewModel,
    extraToppings: @Composable () -> Unit,
    navBack: () -> Unit,
    modifier: Modifier = Modifier) {

   var showBottomSheet by remember { mutableStateOf(false) }

    val pizza = detailViewmodel.mPizza.collectAsStateWithLifecycle().value

    Box(modifier = modifier.safeContentPadding()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(onClick = navBack) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }

            pizza?.let {pzz ->
                pzz.image.let { img ->
                    when(img){
                        is UiImage.ResourceImage -> {
                            Image(
                                painter = painterResource(id = img.resourceId),
                                contentDescription = null
                            )
                        }
                        is UiImage.UrlImage -> {
                            img.ToImage()
                        }
                    }
                }
                Text(pzz.name().asString())
                Text(pzz.description().map {
                    it.asString()
                }.fastJoinToString ())

                Text(pzz.extraToppings()
                    .map {
                       it.first + "x" + it.second.asString()
                    }.fastJoinToString()
                )
            }
            Button(onClick = { showBottomSheet = true }) {
                Text("Extra toppings")
            }

        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
            ) {
                extraToppings()
            }
        }
    }
}