package com.denisbrandi.androidrealca.cart.di

import com.denisbrandi.androidrealca.cache.CacheProvider
import com.denisbrandi.androidrealca.cart.data.repository.RealCartRepository
import com.denisbrandi.androidrealca.cart.domain.usecase.*
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser

class CartComponentDI(
    private val cacheProvider: CacheProvider,
    private val getUser: GetUser
) {
    private val cartRepository by lazy {
        RealCartRepository(cacheProvider)
    }

    val updateCartItem: UpdateCartItem by lazy {
        UpdateCartItemUseCase(getUser, cartRepository)
    }

    val observeUserCart: ObserveUserCart by lazy {
        ObserveUserCartUseCase(getUser, cartRepository)
    }
    val addCartItem: AddCartItem by lazy {
        AddCartItemUseCase(getUser, cartRepository, updateCartItem)
    }
}
