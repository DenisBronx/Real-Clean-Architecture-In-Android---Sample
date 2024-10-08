package com.denisbrandi.androidrealca.wishlist.domain.repository

import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import kotlinx.coroutines.flow.Flow

class TestWishlistRepository : WishlistRepository {
    val addToWishlistInvocations: MutableList<Pair<String, WishlistItem>> = mutableListOf()
    val removeWishlistInvocations: MutableList<Pair<String, String>> = mutableListOf()
    val wishlistUpdates = mutableMapOf<String, Flow<List<WishlistItem>>>()

    override fun addToWishlist(userId: String, wishlistItem: WishlistItem) {
        addToWishlistInvocations.add(userId to wishlistItem)
    }

    override fun removeFromWishlist(userId: String, wishlistItemId: String) {
        removeWishlistInvocations.add(userId to wishlistItemId)
    }

    override fun observeWishlist(userId: String): Flow<List<WishlistItem>> {
        return wishlistUpdates[userId] ?: throw IllegalStateException("no stubbing for userId")
    }
}
