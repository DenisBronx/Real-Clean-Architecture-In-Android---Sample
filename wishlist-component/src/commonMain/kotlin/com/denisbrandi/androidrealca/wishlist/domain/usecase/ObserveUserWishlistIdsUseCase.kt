package com.denisbrandi.androidrealca.wishlist.domain.usecase

import kotlinx.coroutines.flow.*

internal class ObserveUserWishlistIdsUseCase(
    private val observeUserWishlist: ObserveUserWishlist
) : ObserveUserWishlistIds {
    override fun invoke(): Flow<List<String>> {
        return observeUserWishlist().map { list -> list.map { item -> item.id } }
    }
}
