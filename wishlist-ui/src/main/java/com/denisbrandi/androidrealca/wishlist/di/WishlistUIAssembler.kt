package com.denisbrandi.androidrealca.wishlist.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denisbrandi.androidrealca.cart.di.CartComponentAssembler
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.presentation.view.WishlistScreen
import com.denisbrandi.androidrealca.wishlist.presentation.viewmodel.*

class WishlistUIAssembler(
    private val wishlistComponentAssembler: WishlistComponentAssembler,
    private val cartComponentAssembler: CartComponentAssembler
) {
    @Composable
    private fun makeWishlistViewModel(): WishlistViewModel {
        return viewModel {
            RealWishlistViewModel(
                observeUserWishlist = wishlistComponentAssembler.makeObserveUserWishlist(),
                removeFromWishlist = wishlistComponentAssembler.makeRemoveFromWishlist(),
                addCartItem = cartComponentAssembler.makeAddCartItem(),
                stateDelegate = StateDelegate()
            )
        }
    }

    @Composable
    fun WishlistScreenDestination() {
        WishlistScreen(makeWishlistViewModel())
    }
}
