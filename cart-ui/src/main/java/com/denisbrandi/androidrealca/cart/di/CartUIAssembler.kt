package com.denisbrandi.androidrealca.cart.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denisbrandi.androidrealca.cart.presentation.view.CartScreen
import com.denisbrandi.androidrealca.cart.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateDelegate

class CartUIAssembler(
    private val cartComponentAssembler: CartComponentAssembler
) {
    @Composable
    private fun makeCartViewModel(): CartViewModel {
        return viewModel {
            RealCartViewModel(
                cartComponentAssembler.observeUserCart,
                cartComponentAssembler.updateCartItem,
                StateDelegate()
            )
        }
    }

    @Composable
    fun CartScreenDestination() {
        CartScreen(makeCartViewModel())
    }
}
