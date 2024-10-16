package com.denisbrandi.androidrealca.wishlist.di

import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.cart.domain.usecase.*
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.presentation.view.WishlistScreen
import com.denisbrandi.androidrealca.wishlist.presentation.viewmodel.*

class WishlistUIDI(
    private val wishlistComponentDI: WishlistComponentDI,
    private val addCartItem: AddCartItem
) {
    private fun makeWishlistViewModel(): WishlistViewModel {
        return RealWishlistViewModel(
            wishlistComponentDI.observeUserWishlist,
            wishlistComponentDI.removeFromWishlist,
            addCartItem,
            StateDelegate()
        )
    }

    @Composable
    fun WishlistScreenDI() {
        WishlistScreen(makeWishlistViewModel())
    }
}
