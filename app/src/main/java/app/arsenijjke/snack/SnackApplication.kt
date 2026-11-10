package app.arsenijjke.snack

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

//TODO("Setup Global Configuration")
@HiltAndroidApp
class SnackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}