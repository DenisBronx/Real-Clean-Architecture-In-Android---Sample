package com.denisbrandi.androidrealca.plp.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denisbrandi.androidrealca.cart.di.CartComponentAssembler
import com.denisbrandi.androidrealca.plp.presentation.view.PLPScreen
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.product.di.ProductComponentAssembler
import com.denisbrandi.androidrealca.user.di.UserComponentAssembler
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.di.WishlistComponentAssembler

class PLPUIAssembler(
    private val userComponentAssembler: UserComponentAssembler,
    private val productComponentAssembler: ProductComponentAssembler,
    private val wishlistComponentAssembler: WishlistComponentAssembler,
    private val cartComponentAssembler: CartComponentAssembler
) {
    @Composable
    private fun makePLPViewModel(): PLPViewModel {
        return viewModel {
            RealPLPViewModel(
                getUser = userComponentAssembler.makeGetUser(),
                getProducts = productComponentAssembler.makeGetProducts(),
                observeUserWishlistIds = wishlistComponentAssembler.makeObserveUserWishlistIds(),
                addToWishlist = wishlistComponentAssembler.makeAddToWishlist(),
                removeFromWishlist = wishlistComponentAssembler.makeRemoveFromWishlist(),
                addCartItem = cartComponentAssembler.makeAddCartItem(),
                stateDelegate = StateDelegate()
            )
        }
    }

    @Composable
    fun PLPScreenDestination() {
        PLPScreen(makePLPViewModel())
    }
}
