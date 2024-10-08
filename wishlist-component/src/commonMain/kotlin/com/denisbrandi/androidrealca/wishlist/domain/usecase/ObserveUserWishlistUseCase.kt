package com.denisbrandi.androidrealca.wishlist.domain.usecase

import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.repository.WishlistRepository
import kotlinx.coroutines.flow.Flow

internal class ObserveUserWishlistUseCase(
    private val getUser: GetUser,
    private val wishlistRepository: WishlistRepository
) : ObserveUserWishlist {
    override fun invoke(): Flow<List<WishlistItem>> {
        return wishlistRepository.observeWishlist(getUser().id)
    }
}
