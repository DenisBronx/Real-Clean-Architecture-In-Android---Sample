package com.denisbrandi.androidrealca.cart.domain.repository

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

internal interface CartRepository {
    fun updateCartItem(userId: String, cartItem: CartItem)
    fun observeCart(userId: String): Flow<List<CartItem>>
}
