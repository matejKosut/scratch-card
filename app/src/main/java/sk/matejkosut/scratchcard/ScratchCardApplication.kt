package sk.matejkosut.scratchcard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScratchCardApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        
    }
}