package com.denisbrandi.androidrealca.wishlist.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.presentation.viewmodel.*
import kotlinx.coroutines.flow.*

@Preview
@Composable
fun PreviewPLPEmptyState() {
    WishlistScreen(createViewModelWithState(WishlistState(emptyList())))
}

@Preview
@Composable
fun PreviewPLPProductsState() {
    WishlistScreen(createViewModelWithState(WishlistState(wishlist)))
}

private fun createViewModelWithState(state: WishlistState): WishlistViewModel {
    return TestWishlistViewModel(MutableStateFlow(state))
}

private class TestWishlistViewModel(
    stateFlow: StateFlow<WishlistState>
) : WishlistViewModel,
    StateViewModel<WishlistState> {
    override val state = stateFlow

    override fun removeItemFromWishlist(wishlistItemId: String) {}

    override fun addProductToCart(wishlistItem: WishlistItem) {}
}
