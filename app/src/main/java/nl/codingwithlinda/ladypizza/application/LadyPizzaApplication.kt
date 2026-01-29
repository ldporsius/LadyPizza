package nl.codingwithlinda.ladypizza.application

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

class LadyPizzaApplication: Application() {

    override fun onCreate() {
        super.onCreate()


        Firebase.initialize(this)
    }

}