package app.arsenijjke.snack

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//TODO("Setup Global Configuration")
@HiltAndroidApp
class SnackApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}