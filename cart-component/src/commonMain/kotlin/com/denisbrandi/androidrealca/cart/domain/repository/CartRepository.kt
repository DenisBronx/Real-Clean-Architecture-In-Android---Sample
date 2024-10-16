package com.denisbrandi.androidrealca.cart.domain.repository

import com.denisbrandi.androidrealca.cart.domain.model.*
import kotlinx.coroutines.flow.Flow

internal interface CartRepository {
    fun updateCartItem(userId: String, cartItem: CartItem)
    fun observeCart(userId: String): Flow<Cart>
    fun getCart(userId: String): Cart
}
