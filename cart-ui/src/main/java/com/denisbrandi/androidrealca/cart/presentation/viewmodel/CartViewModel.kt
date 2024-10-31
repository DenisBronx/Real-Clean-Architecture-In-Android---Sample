package com.denisbrandi.androidrealca.cart.presentation.viewmodel

import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.viewmodel.StateViewModel

internal interface CartViewModel : StateViewModel<CartScreenState> {
    fun updateCartItemQuantity(cartItem: CartItem)
}

internal data class CartScreenState(val cart: Cart)
