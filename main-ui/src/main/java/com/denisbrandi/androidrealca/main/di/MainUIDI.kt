package com.denisbrandi.androidrealca.main.di

import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.cart.domain.usecase.ObserveUserCart
import com.denisbrandi.androidrealca.main.presentation.view.MainScreen
import com.denisbrandi.androidrealca.main.presentation.view.navigation.BottomNavRouter
import com.denisbrandi.androidrealca.main.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.domain.usecase.ObserveUserWishlistIds

class MainUIDI(
    private val observeUserWishlistIds: ObserveUserWishlistIds,
    private val observeUserCart: ObserveUserCart
) {

    private fun makeMainViewModel(): MainViewModel {
        return RealMainViewModel(observeUserWishlistIds, observeUserCart, StateDelegate())
    }

    @Composable
    fun MainScreenDI(
        bottomNavRouter: BottomNavRouter
    ) {
        MainScreen(
            mainViewModel = makeMainViewModel(),
            bottomNavRouter = bottomNavRouter
        )
    }
}
