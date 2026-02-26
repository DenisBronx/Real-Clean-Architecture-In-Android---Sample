package com.denisbrandi.androidrealca

import android.app.Application
import com.denisbrandi.androidrealca.di.CompositionRoot

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        CompositionRoot.compose(this)
    }
}
