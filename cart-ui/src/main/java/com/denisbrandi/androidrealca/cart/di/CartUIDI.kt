package com.denisbrandi.androidrealca.cart.di

import androidx.compose.runtime.Composable
import com.denisbrandi.androidrealca.cart.presentation.view.CartScreen
import com.denisbrandi.androidrealca.cart.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateDelegate

class CartUIDI(
    private val cartComponentDI: CartComponentDI
) {
    private fun makeCartViewModel(): CartViewModel {
        return RealCartViewModel(
            cartComponentDI.observeUserCart,
            cartComponentDI.updateCartItem,
            StateDelegate()
        )
    }

    @Composable
    fun CartScreenDI() {
        CartScreen(makeCartViewModel())
    }
}
