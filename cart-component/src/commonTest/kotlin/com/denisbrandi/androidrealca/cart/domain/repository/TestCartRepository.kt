package com.denisbrandi.androidrealca.cart.domain.repository

import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import kotlinx.coroutines.flow.Flow

class TestCartRepository : CartRepository {
    val updateCartItemInvocations: MutableList<Pair<String, CartItem>> = mutableListOf()
    val cartUpdates = mutableMapOf<String, Flow<List<CartItem>>>()

    override fun updateCartItem(userId: String, cartItem: CartItem) {
        updateCartItemInvocations.add(userId to cartItem)
    }

    override fun observeCart(userId: String): Flow<List<CartItem>> {
        return cartUpdates[userId] ?: throw IllegalStateException("no stubbing for userId")
    }
}
