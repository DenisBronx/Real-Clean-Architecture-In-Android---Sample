package com.denisbrandi.androidrealca.plp.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.viewmodel.*

@Preview
@Composable
fun PreviewPLPDefaultState() {
    PLPScreen(createViewModelWithState(PLPState(fullName = "Full Name")))
}

@Preview
@Composable
fun PreviewPLPLoadingState() {
    PLPScreen(
        createViewModelWithState(
            PLPState(
                fullName = "Full Name",
                contentType = ContentType.Loading
            )
        )
    )
}

@Preview
@Composable
fun PreviewPLPErrorState() {
    PLPScreen(
        createViewModelWithState(
            PLPState(
                fullName = "Full Name",
                contentType = ContentType.Error
            )
        )
    )
}

@Preview
@Composable
fun PreviewPLPEmptyState() {
    PLPScreen(
        createViewModelWithState(
            PLPState(
                fullName = "Full Name",
                contentType = ContentType.Content(emptyList())
            )
        )
    )
}

@Preview
@Composable
fun PreviewPLPProductsState() {
    PLPScreen(
        createViewModelWithState(
            PLPState(
                fullName = "Full Name",
                contentType = ContentType.Content(productList)
            )
        )
    )
}

private fun createViewModelWithState(state: PLPState): PLPViewModel {
    val stateDelegate = StateDelegate<PLPState>()
    stateDelegate.setDefaultState(state)
    return TestPLPViewModel(stateDelegate)
}

private class TestPLPViewModel(
    stateDelegate: StateDelegate<PLPState>
) : PLPViewModel,
    StateViewModel<PLPState> by stateDelegate {
    override fun loadProducts() {}
    override fun isFavourite(productId: String): Boolean {
        return productId.toInt() % 2 == 0
    }

    override fun addProductToWishlist(product: Product) {}

    override fun removeProductFromWishlist(productId: String) {}

    override fun addProductToCart(product: Product) {}
}
