package com.denisbrandi.androidrealca.plp.di

import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.plp.presentation.view.PLPScreen
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.product.domain.usecase.GetProducts
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.viewmodel.StateDelegate
import com.denisbrandi.androidrealca.wishlist.domain.usecase.ObserveUserWishlistIds

class PLPUIDI(
    private val getUser: GetUser,
    private val getProducts: GetProducts,
    private val observeUserWishlistIds: ObserveUserWishlistIds
) {
    private fun makePLPViewModel(): PLPViewModel {
        return RealPLPViewModel(getUser, getProducts, observeUserWishlistIds, StateDelegate())
    }

    @Composable
    fun PLPScreenDI() {
        PLPScreen(makePLPViewModel())
    }
}
