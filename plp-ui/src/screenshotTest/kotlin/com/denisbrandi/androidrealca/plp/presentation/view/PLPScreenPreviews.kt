package com.denisbrandi.androidrealca.plp.presentation.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.android.tools.screenshot.PreviewTest
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.product.domain.model.Product
import com.denisbrandi.androidrealca.viewmodel.StateViewModel
import kotlinx.coroutines.flow.*

@PreviewTest
@Preview
@Composable
fun PreviewPLPDefaultState() {
    PLPScreen(createViewModelWithState(PLPScreenState(fullName = "Full Name")))
}

@PreviewTest
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

@PreviewTest
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

@PreviewTest
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

@PreviewTest
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
