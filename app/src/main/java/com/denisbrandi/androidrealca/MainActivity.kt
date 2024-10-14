package com.denisbrandi.androidrealca

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import com.denisbrandi.androidrealca.di.Injector
import com.denisbrandi.androidrealca.navigation.Navigation
import com.denisbrandi.androidrealca.ui.theme.RealCleanArchitectureInAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.start(this.applicationContext)
        enableEdgeToEdge()
        setContent {
            RealCleanArchitectureInAndroidTheme {
                Navigation()
            }
        }
    }
}
