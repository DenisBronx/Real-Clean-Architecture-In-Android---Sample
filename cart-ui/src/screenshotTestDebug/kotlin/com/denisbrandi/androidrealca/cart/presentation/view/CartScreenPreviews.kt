package com.denisbrandi.androidrealca.cart.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import kotlinx.coroutines.flow.*

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
    return TestCartViewModel(MutableStateFlow(state))
}

private class TestCartViewModel(
    stateFlow: StateFlow<CartState>
) : CartViewModel,
    StateViewModel<CartState> {
    override val state = stateFlow
    override fun updateCartItemQuantity(cartItem: CartItem) {}
}
