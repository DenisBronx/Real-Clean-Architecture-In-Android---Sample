package com.denisbrandi.androidrealca.cart.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.cart.domain.model.Cart
import com.denisbrandi.androidrealca.cart.domain.usecase.ObserveUserCart
import com.denisbrandi.androidrealca.viewmodel.*
import kotlinx.coroutines.flow.*

internal class RealCartViewModel(
    observeUserCart: ObserveUserCart,
    private val stateDelegate: StateDelegate<CartState>
) : CartViewModel, StateViewModel<CartState> by stateDelegate, ViewModel() {

    init {
        stateDelegate.setDefaultState(CartState(Cart(emptyList())))
        observeUserCart().onEach { cart ->
            stateDelegate.updateState { CartState(cart) }
        }.launchIn(viewModelScope)
    }
}
