package com.denisbrandi.androidrealca.wishlist.domain.repository

import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import kotlinx.coroutines.flow.Flow

internal interface WishlistRepository {
    fun addToWishlist(userId: String, wishlistItem: WishlistItem)
    fun removeFromWishlist(userId: String, wishlistItemId: String)
    fun observeWishlist(userId: String): Flow<List<WishlistItem>>
}
