package com.denisbrandi.androidrealca.wishlist.presentation.view.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.viewmodel.*
import com.denisbrandi.androidrealca.wishlist.presentation.view.WishlistScreen
import com.denisbrandi.androidrealca.wishlist.presentation.viewmodel.*

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
    val stateDelegate = StateDelegate<WishlistState>()
    stateDelegate.setDefaultState(state)
    return TestWishlistViewModel(stateDelegate)
}

private class TestWishlistViewModel(
    stateDelegate: StateDelegate<WishlistState>
) : WishlistViewModel,
    StateViewModel<WishlistState> by stateDelegate {

    override fun removeItemFromWishlist(wishlistItemId: String) {}
}
