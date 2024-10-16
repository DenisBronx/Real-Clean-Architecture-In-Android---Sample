package com.denisbrandi.androidrealca.main.di

import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.cart.domain.usecase.ObserveUserCart
import com.denisbrandi.androidrealca.main.presentation.view.MainScreen
import com.denisbrandi.androidrealca.wishlist.domain.usecase.*

class MainUIDI(
    private val observeUserWishlistIds: ObserveUserWishlistIds,
    private val observeUserCart: ObserveUserCart
) {
    @Composable
    fun MainScreenDI(
        makePLPScreen: @Composable () -> Unit,
        makeWishlistScreen: @Composable () -> Unit,
        makeCartScreen: @Composable () -> Unit
    ) {
        MainScreen(
            observeUserWishlistIds = observeUserWishlistIds,
            observeUserCart = observeUserCart,
            makePLPScreen = makePLPScreen,
            makeWishlistScreen = makeWishlistScreen,
            makeCartScreen = makeCartScreen
        )
    }
}
