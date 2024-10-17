package com.denisbrandi.androidrealca.cart.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.*

@Preview
@Composable
fun PreviewPLPEmptyState() {
    CartScreen(createViewModelWithState(CartState(Cart(emptyList()))))
}

@Preview
@Composable
fun PreviewPLPProductsState() {
    CartScreen(createViewModelWithState(CartState(Cart((cartItems)))))
}

private fun createViewModelWithState(state: CartState): CartViewModel {
    val stateDelegate = StateDelegate<CartState>()
    stateDelegate.setDefaultState(state)
    return TestCartViewModel(stateDelegate)
}

private class TestCartViewModel(
    stateDelegate: StateDelegate<CartState>
) : CartViewModel,
    StateViewModel<CartState> by stateDelegate {
    override fun updateCartItemQuantity(cartItem: CartItem) {}
}
