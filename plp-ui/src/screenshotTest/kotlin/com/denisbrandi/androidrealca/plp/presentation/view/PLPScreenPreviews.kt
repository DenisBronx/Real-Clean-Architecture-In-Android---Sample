package com.denisbrandi.androidrealca.plp.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import kotlinx.coroutines.flow.*

@Preview
@Composable
fun PreviewPLPDefaultState() {
    PLPScreen(createViewModelWithState(PLPScreenState(fullName = "Full Name")))
}

@Preview
@Composable
fun PreviewPLPLoadingState() {
    PLPScreen(
        createViewModelWithState(
            PLPScreenState(
                fullName = "Full Name",
                displayState = DisplayState.Loading
            )
        )
    )
}

@Preview
@Composable
fun PreviewPLPErrorState() {
    PLPScreen(
        createViewModelWithState(
            PLPScreenState(
                fullName = "Full Name",
                displayState = DisplayState.Error
            )
        )
    )
}

@Preview
@Composable
fun PreviewPLPEmptyState() {
    PLPScreen(
        createViewModelWithState(
            PLPScreenState(
                fullName = "Full Name",
                displayState = DisplayState.Content(emptyList())
            )
        )
    )
}

@Preview
@Composable
fun PreviewPLPProductsState() {
    PLPScreen(
        createViewModelWithState(
            PLPScreenState(
                fullName = "Full Name",
                displayState = DisplayState.Content(productList)
            )
        )
    )
}

private fun createViewModelWithState(state: PLPScreenState): PLPViewModel {
    return TestPLPViewModel(MutableStateFlow(state))
}

private class TestPLPViewModel(
    stateFlow: StateFlow<PLPScreenState>
) : PLPViewModel,
    StateViewModel<PLPScreenState> {
    override val state = stateFlow
    override fun loadProducts() {}
    override fun isFavourite(productId: String): Boolean {
        return productId.toInt() % 2 == 0
    }

    override fun addProductToWishlist(product: Product) {}

    override fun removeProductFromWishlist(productId: String) {}

    override fun addProductToCart(product: Product) {}
}
