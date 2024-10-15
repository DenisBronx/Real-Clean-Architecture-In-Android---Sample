package com.denisbrandi.androidrealca.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.denisbrandi.androidrealca.di.injector
import kotlinx.serialization.Serializable

@Serializable
object NavProducts

@Serializable
object NavWishlist

@Serializable
object NavCart

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector
)

val topLevelRoutes = listOf(
    TopLevelRoute("Products", NavProducts, Icons.Filled.Search),
    TopLevelRoute("Wishlist", NavWishlist, Icons.Outlined.FavoriteBorder),
    TopLevelRoute("Cart", NavCart, Icons.Filled.ShoppingCart)
)

@Composable
fun MainScreen() {
    var navigationSelectedItem by remember {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Box(Modifier.size(0.dp)) },
        bottomBar = {
            NavigationBar {
                topLevelRoutes.forEachIndexed { index, navigationItem ->
                    NavigationBarItem(
                        icon = {
                            Icon(navigationItem.icon, contentDescription = navigationItem.name)
                        },
                        label = { Text(navigationItem.name) },
                        selected = index == navigationSelectedItem,
                        onClick = {
                            navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = topLevelRoutes.first().route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable<NavProducts> {
                injector.plpUIDI.PLPScreenDI()
            }
            composable<NavWishlist> {
            }
            composable<NavCart> {
            }
        }
    }
}
