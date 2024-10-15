package com.denisbrandi.androidrealca.plp.presentation.viewmodel

import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.viewmodel.StateViewModel

interface PLPViewModel : StateViewModel<PLPState> {
    fun loadProducts()
}

data class PLPState(
    val fullName: String,
    val wishlistIds: List<String> = emptyList(),
    val contentType: ContentType? = null
)

sealed interface ContentType {
    data object Loading : ContentType
    data object Error : ContentType
    data class Content(val products: List<Product>) : ContentType
}
