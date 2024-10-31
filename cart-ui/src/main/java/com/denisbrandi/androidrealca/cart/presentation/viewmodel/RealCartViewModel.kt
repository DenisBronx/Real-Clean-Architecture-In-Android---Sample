package com.denisbrandi.androidrealca.cart.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.domain.usecase.*
import com.denisbrandi.androidrealca.viewmodel.*
import kotlinx.coroutines.flow.*

internal class RealCartViewModel(
    observeUserCart: ObserveUserCart,
    private val updateCartItem: UpdateCartItem,
    private val stateDelegate: StateDelegate<CartScreenState>
) : CartViewModel, StateViewModel<CartScreenState> by stateDelegate, ViewModel() {

    init {
        stateDelegate.setDefaultState(CartScreenState(Cart(emptyList())))
        observeUserCart().onEach { cart ->
            stateDelegate.updateState { CartScreenState(cart) }
        }.launchIn(viewModelScope)
    }

    override fun updateCartItemQuantity(cartItem: CartItem) {
        updateCartItem(cartItem)
    }
}
