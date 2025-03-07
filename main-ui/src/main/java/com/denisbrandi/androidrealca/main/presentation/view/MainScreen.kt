package com.denisbrandi.androidrealca.main.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.denisbrandi.androidrealca.designsystem.bottomNavElevation
import com.denisbrandi.androidrealca.main.presentation.viewmodel.*
import com.denisbrandi.androidrealca.main.ui.R
import kotlinx.serialization.Serializable

@Serializable
internal object NavProducts

@Serializable
internal object NavWishlist

@Serializable
internal object NavCart

internal data class TopLevelRoute<out T : Any>(
    val name: String,
    val route: T,
    val icon: ImageVector
)

@Composable
private fun topLevelRoutes() = listOf(
    TopLevelRoute(stringResource(R.string.nav_products), NavProducts, Icons.Filled.Search),
    TopLevelRoute(
        stringResource(R.string.nav_wishlist),
        NavWishlist,
        Icons.Outlined.FavoriteBorder
    ),
    TopLevelRoute(stringResource(R.string.nav_cart), NavCart, Icons.Filled.ShoppingCart)
)

@Composable
internal fun MainScreen(
    mainViewModel: MainViewModel,
    makePLPScreen: @Composable () -> Unit,
    makeWishlistScreen: @Composable () -> Unit,
    makeCartScreen: @Composable () -> Unit
) {
    val navController = rememberNavController()
    val topLevelRoutes = topLevelRoutes()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { Box(Modifier.size(0.dp)) },
        bottomBar = {
            BottomNavigationBar(mainViewModel, topLevelRoutes, navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = topLevelRoutes.first().route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable<NavProducts> { makePLPScreen() }
            composable<NavWishlist> { makeWishlistScreen() }
            composable<NavCart> { makeCartScreen() }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    mainViewModel: MainViewModel,
    topLevelRoutes: List<TopLevelRoute<Any>>,
    navController: NavHostController
) {
    val state by mainViewModel.state.collectAsState()
    val navigationSelectedItem = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(
        modifier = Modifier.shadow(elevation = bottomNavElevation)
    ) {
        topLevelRoutes.forEachIndexed { index, navigationItem ->
            NavigationItem(
                index,
                state,
                navigationItem,
                navigationSelectedItem,
                navController
            )
        }
    }
}

@Composable
private fun RowScope.NavigationItem(
    index: Int,
    state: MainScreenState,
    navigationItem: TopLevelRoute<Any>,
    navigationSelectedItem: MutableState<Int>,
    navController: NavHostController
) {
    NavigationBarItem(
        icon = {
            BottomBarIcon(
                index,
                state.wishlistBadge,
                state.cartBadge,
                navigationItem
            )
        },
        label = { Text(navigationItem.name) },
        selected = index == navigationSelectedItem.value,
        onClick = {
            navigationSelectedItem.value = index
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

@Composable
private fun BottomBarIcon(
    index: Int,
    wishlistCount: Int,
    cartItemsCount: Int,
    navigationItem: TopLevelRoute<Any>
) {
    val count = when (index) {
        1 -> {
            wishlistCount
        }

        2 -> {
            cartItemsCount
        }

        else -> 0
    }
    if (count == 0) {
        Icon(navigationItem.icon, contentDescription = navigationItem.name)
    } else {
        BadgedBox(
            badge = {
                Badge {
                    Text(text = count.toString())
                }
            }
        ) {
            Icon(navigationItem.icon, contentDescription = navigationItem.name)
        }
    }
}
