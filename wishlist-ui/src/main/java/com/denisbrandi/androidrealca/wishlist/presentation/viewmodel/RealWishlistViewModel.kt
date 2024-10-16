package com.denisbrandi.androidrealca.wishlist.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import com.denisbrandi.androidrealca.cart.domain.usecase.*
import com.denisbrandi.androidrealca.viewmodel.*
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.usecase.*
import kotlinx.coroutines.flow.*

internal class RealWishlistViewModel(
    observeUserWishlist: ObserveUserWishlist,
    private val removeFromWishlist: RemoveFromWishlist,
    private val addCartItem: AddCartItem,
    private val stateDelegate: StateDelegate<WishlistState>
) : WishlistViewModel, StateViewModel<WishlistState> by stateDelegate, ViewModel() {
    init {
        stateDelegate.setDefaultState(WishlistState())
        observeUserWishlist().onEach { wishlistItems ->
            stateDelegate.updateState { WishlistState(wishlistItems) }
        }.launchIn(viewModelScope)
    }

    override fun removeItemFromWishlist(wishlistItemId: String) {
        removeFromWishlist(wishlistItemId)
    }

    override fun addProductToCart(wishlistItem: WishlistItem) {
        addCartItem(
            CartItem(
                id = wishlistItem.id,
                name = wishlistItem.name,
                money = wishlistItem.money,
                imageUrl = wishlistItem.imageUrl,
                quantity = 1
            )
        )
    }
}
