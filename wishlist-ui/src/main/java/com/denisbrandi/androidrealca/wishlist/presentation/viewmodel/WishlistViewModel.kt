package com.denisbrandi.androidrealca.wishlist.presentation.viewmodel

import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem

internal interface WishlistViewModel : StateViewModel<WishlistScreenState> {
    fun removeItemFromWishlist(wishlistItemId: String)

    fun addProductToCart(wishlistItem: WishlistItem)
}

data class WishlistScreenState(val wishlistItems: List<WishlistItem> = emptyList())
