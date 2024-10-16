package com.denisbrandi.androidrealca.plp.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denisbrandi.androidrealca.cart.domain.usecase.AddCartItem
import com.denisbrandi.androidrealca.plp.presentation.view.PLPScreen
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.product.domain.usecase.GetProducts
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.di.WishlistComponentDI

class PLPUIDI(
    private val getUser: GetUser,
    private val getProducts: GetProducts,
    private val wishlistComponentDI: WishlistComponentDI,
    private val addCartItem: AddCartItem
) {
    @Composable
    private fun makePLPViewModel(): PLPViewModel {
        return viewModel {
            RealPLPViewModel(
                getUser,
                getProducts,
                wishlistComponentDI.observeUserWishlistIds,
                wishlistComponentDI.addToWishlist,
                wishlistComponentDI.removeFromWishlist,
                addCartItem,
                StateDelegate()
            )
        }
    }

    @Composable
    fun PLPScreenDI() {
        PLPScreen(makePLPViewModel())
    }
}
