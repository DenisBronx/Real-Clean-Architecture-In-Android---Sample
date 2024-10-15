package com.denisbrandi.androidrealca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.denisbrandi.androidrealca.di.injector
import com.denisbrandi.androidrealca.main.presentation.view.MainScreen
import kotlinx.serialization.Serializable

@Serializable
object NavSplash

@Serializable
object NavLogin

@Serializable
object NavMain

@Composable
fun RootNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavSplash) {
        composable<NavSplash> {
            val destination: Any = if (injector.isUserLoggedIn()) {
                NavMain
            } else {
                NavLogin
            }
            navController.navigate(route = destination)
        }
        composable<NavLogin> {
            injector.onboardingUIDI.LoginScreenDI {
                navController.navigate(route = NavMain)
            }
        }
        composable<NavMain> {
            MainScreen(injector.observeUserWishlist, injector.observeUserCart)
        }
    }
}
