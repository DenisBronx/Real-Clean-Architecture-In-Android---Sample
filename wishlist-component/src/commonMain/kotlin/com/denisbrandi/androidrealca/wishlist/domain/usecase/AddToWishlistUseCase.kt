package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.repository.WishlistRepository

internal class AddToWishlistUseCase(
    private val getUser: GetUser,
    private val wishlistRepository: WishlistRepository
) : AddToWishlist {
    override fun invoke(wishlistItem: WishlistItem) {
        wishlistRepository.addToWishlist(getUser().id, wishlistItem)
    }
}
