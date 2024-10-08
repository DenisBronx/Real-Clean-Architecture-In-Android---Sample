package com.denisbrandi.androidrealca.wishlist.di

import com.denisbrandi.androidrealca.cache.CacheProvider
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.wishlist.data.repository.RealWishlistRepository
import com.denisbrandi.androidrealca.wishlist.domain.usecase.*

class WishlistComponentDI(
    private val cacheProvider: CacheProvider,
    private val getUser: GetUser
) {

    private val wishlistRepository by lazy {
        RealWishlistRepository(cacheProvider)
    }

    val addToWishlist: AddToWishlist by lazy {
        AddToWishlistUseCase(getUser, wishlistRepository)
    }

    val removeFromWishlist: RemoveFromWishlist by lazy {
        RemoveFromWishlistUseCase(getUser, wishlistRepository)
    }

    val observeUserWishlist: ObserveUserWishlist by lazy {
        ObserveUserWishlistUseCase(getUser, wishlistRepository)
    }
}
