package com.denisbrandi.androidrealca.wishlist.di

import com.denisbrandi.androidrealca.cache.CacheProvider
import com.denisbrandi.androidrealca.user.di.UserComponentAssembler
import com.denisbrandi.androidrealca.wishlist.data.repository.RealWishlistRepository
import com.denisbrandi.androidrealca.wishlist.domain.usecase.*

class WishlistComponentAssembler(
    private val cacheProvider: CacheProvider,
    private val userComponentAssembler: UserComponentAssembler
) {

    private val wishlistRepository by lazy {
        RealWishlistRepository(cacheProvider)
    }

    fun makeAddToWishlist(): AddToWishlist = AddToWishlistUseCase(
        getUser = userComponentAssembler.makeGetUser(),
        wishlistRepository = wishlistRepository
    )

    fun makeRemoveFromWishlist(): RemoveFromWishlist = RemoveFromWishlistUseCase(
        getUser = userComponentAssembler.makeGetUser(),
        wishlistRepository = wishlistRepository
    )

    fun makeObserveUserWishlist(): ObserveUserWishlist = ObserveUserWishlistUseCase(
        getUser = userComponentAssembler.makeGetUser(),
        wishlistRepository = wishlistRepository
    )

    fun makeObserveUserWishlistIds(): ObserveUserWishlistIds = ObserveUserWishlistIdsUseCase(
        observeUserWishlist = makeObserveUserWishlist()
    )
}
