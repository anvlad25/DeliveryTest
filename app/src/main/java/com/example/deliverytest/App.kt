package com.example.deliverytest

import android.app.Application
import com.example.deliverytest.di.DiKoin.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(applicationContext)
            modules(koinModule)
        }
    }
}