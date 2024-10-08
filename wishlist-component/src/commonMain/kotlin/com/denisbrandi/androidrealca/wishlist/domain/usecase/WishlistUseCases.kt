package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import kotlinx.coroutines.flow.Flow

fun interface AddToWishlist {
    operator fun invoke(wishlistItem: WishlistItem)
}

fun interface RemoveFromWishlist {
    operator fun invoke(wishlistItemId: String)
}

fun interface ObserveUserWishlist {
    operator fun invoke(): Flow<List<WishlistItem>>
}
