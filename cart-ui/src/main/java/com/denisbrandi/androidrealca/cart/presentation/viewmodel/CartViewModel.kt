package com.denisbrandi.androidrealca.cart.presentation.viewmodel

import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.viewmodel.StateViewModel

internal interface CartViewModel : StateViewModel<CartState> {
    fun updateCartItemQuantity(cartItem: CartItem)
}

internal data class CartState(val cart: Cart)
