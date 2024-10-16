package com.denisbrandi.androidrealca.main.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.cart.domain.usecase.ObserveUserCart
import com.denisbrandi.androidrealca.viewmodel.*
import com.denisbrandi.androidrealca.wishlist.domain.usecase.ObserveUserWishlistIds
import kotlinx.coroutines.flow.*

internal class RealMainViewModel(
    observeUserWishlistIds: ObserveUserWishlistIds,
    observeUserCart: ObserveUserCart,
    stateDelegate: StateDelegate<MainState>
) : MainViewModel, StateViewModel<MainState> by stateDelegate, ViewModel() {

    init {
        stateDelegate.setDefaultState(MainState())
        observeUserWishlistIds().onEach { list ->
            stateDelegate.updateState { state -> state.copy(wishlistBadge = list.size) }
        }.launchIn(viewModelScope)
        observeUserCart().onEach { cart ->
            stateDelegate.updateState { state -> state.copy(cartBadge = cart.getNumberOfItems()) }
        }.launchIn(viewModelScope)
    }
}
