package com.denisbrandi.androidrealca.wishlist.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.presentation.viewmodel.*
import kotlinx.coroutines.flow.*

@PreviewTest
@Preview
@Composable
fun PreviewPLPEmptyState() {
    WishlistScreen(createViewModelWithState(WishlistScreenState(emptyList())))
}

@PreviewTest
@Preview
@Composable
fun PreviewPLPProductsState() {
    WishlistScreen(createViewModelWithState(WishlistScreenState(wishlist)))
}

private fun createViewModelWithState(state: WishlistScreenState): WishlistViewModel {
    return TestWishlistViewModel(MutableStateFlow(state))
}

private class TestWishlistViewModel(
    stateFlow: StateFlow<WishlistScreenState>
) : WishlistViewModel,
    StateViewModel<WishlistScreenState> {
    override val state = stateFlow

    override fun removeItemFromWishlist(wishlistItemId: String) {}

    override fun addProductToCart(wishlistItem: WishlistItem) {}
}
