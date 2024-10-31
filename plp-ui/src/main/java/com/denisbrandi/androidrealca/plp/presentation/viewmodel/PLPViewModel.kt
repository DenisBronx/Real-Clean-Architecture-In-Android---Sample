package com.denisbrandi.androidrealca.plp.presentation.viewmodel

import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.viewmodel.StateViewModel

internal interface PLPViewModel : StateViewModel<PLPScreenState> {
    fun loadProducts()
    fun isFavourite(productId: String): Boolean
    fun addProductToWishlist(product: Product)
    fun removeProductFromWishlist(productId: String)
    fun addProductToCart(product: Product)
}

internal data class PLPScreenState(
    val fullName: String,
    val wishlistIds: List<String> = emptyList(),
    val displayState: DisplayState? = null
)

internal sealed interface DisplayState {
    data object Loading : DisplayState
    data object Error : DisplayState
    data class Content(val products: List<Product>) : DisplayState
}
