package com.denisbrandi.androidrealca

import android.app.Application
import com.denisbrandi.androidrealca.di.Injector

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.start(this)
    }
}
