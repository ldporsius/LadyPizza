package nl.codingwithlinda.ladypizza.application

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import nl.codingwithlinda.ladypizza.application.di.AndroidAppModule
import nl.codingwithlinda.ladypizza.core.domain.model.prices.EuroProductPricing
import nl.codingwithlinda.ladypizza.core.domain.model.shopping_cart.ShoppingCart

class LadyPizzaApplication: Application() {


    companion object{
        lateinit var appModule: AndroidAppModule
        lateinit var shoppingCart: ShoppingCart
    }

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)
        appModule = AndroidAppModule()
        shoppingCart = ShoppingCart(
            EuroProductPricing()
        )
    }

}