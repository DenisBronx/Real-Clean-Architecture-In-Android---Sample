package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.wishlist.domain.repository.WishlistRepository

internal class RemoveFromWishlistUseCase(
    private val getUser: GetUser,
    private val wishlistRepository: WishlistRepository
) : RemoveFromWishlist {
    override fun invoke(wishlistItemId: String) {
        wishlistRepository.removeFromWishlist(getUser().id, wishlistItemId)
    }
}
