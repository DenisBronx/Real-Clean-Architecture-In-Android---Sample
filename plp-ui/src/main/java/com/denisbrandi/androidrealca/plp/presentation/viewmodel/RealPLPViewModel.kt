package com.denisbrandi.androidrealca.plp.presentation.viewmodel

import androidx.lifecycle.*
import com.denisbrandi.androidrealca.cart.domain.model.CartItem
import com.denisbrandi.androidrealca.cart.domain.usecase.AddCartItem
import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.product.domain.usecase.GetProducts
import com.denisbrandi.androidrealca.user.domain.usecase.GetUser
import com.denisbrandi.androidrealca.viewmodel.*
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.domain.usecase.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class RealPLPViewModel(
    getUser: GetUser,
    private val getProducts: GetProducts,
    observeUserWishlistIds: ObserveUserWishlistIds,
    private val addToWishlist: AddToWishlist,
    private val removeFromWishlist: RemoveFromWishlist,
    private val addCartItem: AddCartItem,
    private val stateDelegate: StateDelegate<PLPScreenState>
) : PLPViewModel, StateViewModel<PLPScreenState> by stateDelegate, ViewModel() {

    init {
        stateDelegate.setDefaultState(PLPScreenState(getUser().fullName))
        observeUserWishlistIds().onEach { ids ->
            stateDelegate.updateState { it.copy(wishlistIds = ids) }
        }.launchIn(viewModelScope)
    }

    override fun loadProducts() {
        if (state.value.displayState == null) {
            stateDelegate.updateState {
                it.copy(displayState = DisplayState.Loading)
            }
            viewModelScope.launch {
                getProducts().fold(
                    success = { products ->
                        stateDelegate.updateState {
                            it.copy(
                                displayState = DisplayState.Content(
                                    products
                                )
                            )
                        }
                    },
                    error = {
                        stateDelegate.updateState { it.copy(displayState = DisplayState.Error) }
                    }
                )
            }
        }
    }

    override fun isFavourite(productId: String): Boolean {
        return state.value.wishlistIds.contains(productId)
    }

    override fun addProductToWishlist(product: Product) {
        addToWishlist(WishlistItem(product.id, product.name, product.money, product.imageUrl))
    }

    override fun removeProductFromWishlist(productId: String) {
        removeFromWishlist(productId)
    }

    override fun addProductToCart(product: Product) {
        addCartItem(
            CartItem(
                id = product.id,
                name = product.name,
                money = product.money,
                imageUrl = product.imageUrl,
                quantity = 1
            )
        )
    }
}
