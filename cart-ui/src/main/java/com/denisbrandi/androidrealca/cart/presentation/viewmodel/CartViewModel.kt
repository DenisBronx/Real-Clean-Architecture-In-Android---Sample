package com.denisbrandi.androidrealca.cart.presentation.viewmodel

import com.denisbrandi.androidrealca.cart.domain.model.Cart
import com.denisbrandi.androidrealca.viewmodel.StateViewModel

internal interface CartViewModel : StateViewModel<CartState>

internal data class CartState(val cart: Cart)
