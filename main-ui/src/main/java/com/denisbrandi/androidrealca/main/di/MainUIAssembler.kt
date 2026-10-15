package com.denisbrandi.androidrealca.main.di

import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.cart.di.CartComponentAssembler
import com.denisbrandi.androidrealca.main.presentation.view.MainScreen
import com.denisbrandi.androidrealca.main.presentation.view.navigation.BottomNavRouter
import com.denisbrandi.androidrealca.main.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.di.WishlistComponentAssembler

class MainUIAssembler(
    private val wishlistComponentAssembler: WishlistComponentAssembler,
    private val cartComponentAssembler: CartComponentAssembler
) {

    private fun makeMainViewModel(): MainViewModel {
        return RealMainViewModel(
            observeUserWishlistIds = wishlistComponentAssembler.makeObserveUserWishlistIds(),
            observeUserCart = cartComponentAssembler.makeObserveUserCart(),
            stateDelegate = StateDelegate()
        )
    }

    @Composable
    fun MainScreenDestination(
        bottomNavRouter: BottomNavRouter
    ) {
        MainScreen(
            mainViewModel = makeMainViewModel(),
            bottomNavRouter = bottomNavRouter
        )
    }
}
