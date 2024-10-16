package com.denisbrandi.androidrealca.cart.domain.repository

import com.denisbrandi.androidrealca.cart.domain.model.*
import kotlinx.coroutines.flow.Flow

class TestCartRepository : CartRepository {
    val updateCartItemInvocations: MutableList<Pair<String, CartItem>> = mutableListOf()
    val cartUpdates = mutableMapOf<String, Flow<Cart>>()
    val carts = mutableMapOf<String, Cart>()

    override fun updateCartItem(userId: String, cartItem: CartItem) {
        updateCartItemInvocations.add(userId to cartItem)
    }

    override fun observeCart(userId: String): Flow<Cart> {
        return cartUpdates[userId] ?: throw IllegalStateException("no stubbing for userId")
    }

    override fun getCart(userId: String): Cart {
        return carts[userId] ?: throw IllegalStateException("no stubbing for userId")
    }
}
