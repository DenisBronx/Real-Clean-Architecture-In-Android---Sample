package com.denisbrandi.androidrealca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.denisbrandi.androidrealca.di.compositionRoot
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
            val destination: Any = if (compositionRoot.isUserLoggedIn()) {
                NavMain
            } else {
                NavLogin
            }
            navController.navigate(route = destination)
        }
        composable<NavLogin> {
            compositionRoot.onboardingUIAssembler.LoginScreenDestination(
                onLoggedIn = { navController.navigate(route = NavMain) }
            )
        }
        composable<NavMain> {
            compositionRoot.mainUIAssembler.MainScreenDestination(RealBottomNavRouter)
        }
    }
}

private object RealBottomNavRouter : BottomNavRouter {
    @Composable
    override fun OpenPLPScreen() {
        compositionRoot.plpUIAssembler.PLPScreenDestination()
    }

    @Composable
    override fun OpenWishlistScreen() {
        compositionRoot.wishlistUIAssembler.WishlistScreenDestination()
    }

    @Composable
    override fun OpenCartScreen() {
        compositionRoot.cartUIAssembler.CartScreenDestination()
    }
}
