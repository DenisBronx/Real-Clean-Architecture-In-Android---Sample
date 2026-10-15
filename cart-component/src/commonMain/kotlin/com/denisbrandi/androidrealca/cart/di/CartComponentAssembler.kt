package com.denisbrandi.androidrealca.cart.di

import com.denisbrandi.androidrealca.cache.CacheProvider
import com.denisbrandi.androidrealca.cart.data.repository.RealCartRepository
import com.denisbrandi.androidrealca.cart.domain.usecase.*
import com.denisbrandi.androidrealca.user.di.UserComponentAssembler

class CartComponentAssembler(
    private val cacheProvider: CacheProvider,
    private val userComponentAssembler: UserComponentAssembler
) {
    private val cartRepository by lazy {
        RealCartRepository(cacheProvider)
    }

    fun makeUpdateCartItem(): UpdateCartItem = UpdateCartItemUseCase(
        getUser = userComponentAssembler.makeGetUser(),
        cartRepository = cartRepository
    )

    fun makeObserveUserCart(): ObserveUserCart = ObserveUserCartUseCase(
        getUser = userComponentAssembler.makeGetUser(),
        cartRepository = cartRepository
    )

    fun makeAddCartItem(): AddCartItem = AddCartItemUseCase(
        getUser = userComponentAssembler.makeGetUser(),
        cartRepository = cartRepository,
        updateCartItem = makeUpdateCartItem()
    )
}
