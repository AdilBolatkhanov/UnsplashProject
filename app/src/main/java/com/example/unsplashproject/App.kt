package com.example.unsplashproject

import android.app.Application
import com.example.unsplashproject.di.unsplashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                unsplashModule
            )
        }
    }
}