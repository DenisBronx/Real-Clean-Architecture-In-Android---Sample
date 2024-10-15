package com.denisbrandi.androidrealca.plp.presentation.viewmodel

import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.viewmodel.StateViewModel

internal interface PLPViewModel : StateViewModel<PLPState> {
    fun loadProducts()
    fun isFavourite(productId: String): Boolean
    fun addProductToWishlist(product: Product)
    fun removeProductFromWishlist(productId: String)
}

internal data class PLPState(
    val fullName: String,
    val wishlistIds: List<String> = emptyList(),
    val contentType: ContentType? = null
)

internal sealed interface ContentType {
    data object Loading : ContentType
    data object Error : ContentType
    data class Content(val products: List<Product>) : ContentType
}
