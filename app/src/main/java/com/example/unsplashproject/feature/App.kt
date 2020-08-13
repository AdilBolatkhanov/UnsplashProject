package com.example.unsplashproject.feature

import android.app.Application
import android.content.Context
import com.example.unsplashproject.di.dbModule
import com.example.unsplashproject.di.mainModule
import com.example.unsplashproject.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                mainModule,
                dbModule
            )
        }
    }
}