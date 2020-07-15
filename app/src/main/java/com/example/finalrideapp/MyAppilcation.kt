package com.example.finalrideapp

import android.app.Application
import com.example.finalrideapp.BuildConfig
import timber.log.Timber

class MyAppilcation: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}