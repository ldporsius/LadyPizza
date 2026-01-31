package nl.codingwithlinda.ladypizza.application

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize
import nl.codingwithlinda.ladypizza.application.di.AndroidAppModule

class LadyPizzaApplication: Application() {

    companion object{
        val appModule = AndroidAppModule()
    }

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)
    }

}