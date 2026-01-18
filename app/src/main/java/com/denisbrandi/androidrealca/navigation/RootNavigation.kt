package com.denisbrandi.androidrealca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.denisbrandi.androidrealca.di.injector
import com.denisbrandi.androidrealca.main.presentation.view.navigation.BottomNavRouter
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
            injector.onboardingUIDI.LoginScreenDI(
                onLoggedIn = { navController.navigate(route = NavMain) }
            )
        }
        composable<NavMain> {
            injector.mainUIDI.MainScreenDI(RealBottomNavRouter)
        }
    }
}

private object RealBottomNavRouter : BottomNavRouter {
    @Composable
    override fun OpenPLPScreen() {
        injector.plpUIDI.PLPScreenDI()
    }

    @Composable
    override fun OpenWishlistScreen() {
        injector.wishlistUIDI.WishlistScreenDI()
    }

    @Composable
    override fun OpenCartScreen() {
        injector.cartUIDI.CartScreenDI()
    }
}
