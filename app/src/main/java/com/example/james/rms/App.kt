package com.example.james.rms

import android.app.Application
import com.facebook.stetho.Stetho

/**
 * Created by steve_000 on 18/1/2019.
 * for project RMS
 * package name com.example.james.rms
 */
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}