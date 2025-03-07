package com.denisbrandi.androidrealca

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import com.denisbrandi.androidrealca.designsystem.RealCleanArchitectureInAndroidTheme
import com.denisbrandi.androidrealca.navigation.RootNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealCleanArchitectureInAndroidTheme {
                RootNavigation()
            }
        }
    }
}
