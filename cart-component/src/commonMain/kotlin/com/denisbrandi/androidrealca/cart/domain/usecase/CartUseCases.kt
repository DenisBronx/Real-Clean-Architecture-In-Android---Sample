package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

fun interface UpdateCartItem {
    operator fun invoke(cartItem: CartItem)
}

fun interface ObserveUserCart {
    operator fun invoke(): Flow<List<CartItem>>
}
