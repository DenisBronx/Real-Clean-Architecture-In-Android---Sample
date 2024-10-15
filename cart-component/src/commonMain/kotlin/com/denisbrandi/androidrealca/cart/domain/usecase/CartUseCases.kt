package com.denisbrandi.androidrealca.cart.domain.usecase

import com.denisbrandi.androidrealca.cart.domain.model.*
import kotlinx.coroutines.flow.Flow

fun interface UpdateCartItem {
    operator fun invoke(cartItem: CartItem)
}

fun interface ObserveUserCart {
    operator fun invoke(): Flow<Cart>
}
