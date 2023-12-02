package com.example.tp5

import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set

        fun getAppContext(): Context {
            return instance.applicationContext
        }
    }
}
