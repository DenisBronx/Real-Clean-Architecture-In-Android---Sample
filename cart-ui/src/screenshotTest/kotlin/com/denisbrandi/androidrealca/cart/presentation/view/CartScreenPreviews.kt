package com.denisbrandi.androidrealca.cart.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.presentation.viewmodel.*
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import kotlinx.coroutines.flow.*

@PreviewTest
@Preview
@Composable
fun PreviewPLPEmptyState() {
    CartScreen(createViewModelWithState(CartScreenState(Cart(emptyList()))))
}

@PreviewTest
@Preview
@Composable
fun PreviewPLPProductsState() {
    CartScreen(createViewModelWithState(CartScreenState(Cart((cartItems)))))
}

private fun createViewModelWithState(state: CartScreenState): CartViewModel {
    return TestCartViewModel(MutableStateFlow(state))
}

private class TestCartViewModel(
    stateFlow: StateFlow<CartScreenState>
) : CartViewModel,
    StateViewModel<CartScreenState> {
    override val state = stateFlow
    override fun updateCartItemQuantity(cartItem: CartItem) {}
}
