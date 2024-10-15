package com.denisbrandi.androidrealca.plp.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.product.domain.usecase.GetProducts
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.viewmodel.*
import com.denisbrandi.androidrealca.wishlist.domain.usecase.ObserveUserWishlistIds
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class RealPLPViewModel(
    getUser: GetUser,
    private val getProducts: GetProducts,
    observeUserWishlistIds: ObserveUserWishlistIds,
    private val stateDelegate: StateDelegate<PLPState>
) : PLPViewModel, StateViewModel<PLPState> by stateDelegate, ViewModel() {

    init {
        stateDelegate.setDefaultState(PLPState(getUser().fullName))
        observeUserWishlistIds().onEach { ids ->
            stateDelegate.updateState { it.copy(wishlistIds = ids) }
        }.launchIn(viewModelScope)
    }

    override fun loadProducts() {
        stateDelegate.updateState {
            it.copy(contentType = ContentType.Loading)
        }
        viewModelScope.launch {
            getProducts().fold(
                success = { products ->
                    stateDelegate.updateState { it.copy(contentType = ContentType.Content(products)) }
                },
                error = {
                    stateDelegate.updateState { it.copy(contentType = ContentType.Error) }
                }
            )
        }
    }
}
