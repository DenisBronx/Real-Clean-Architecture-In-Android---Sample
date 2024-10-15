package com.denisbrandi.androidrealca.plp.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.denisbrandi.androidrealca.designsystem.*
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.plp.ui.R
import com.denisbrandi.androidrealca.product.domain.model.Product

@Composable
internal fun PLPScreen(
    plpViewModel: PLPViewModel
) {
    val plpState = plpViewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopBar(stringResource(R.string.plp_welcome, plpState.value.fullName))
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Body(plpViewModel, plpState.value)
        }
    }
    LaunchedEffect(Unit) {
        plpViewModel.loadProducts()
    }
}

@Composable
private fun Body(plpViewModel: PLPViewModel, plpState: PLPState) {
    when (val contentType = plpState.contentType) {
        is ContentType.Error -> {
            RetryErrorView { plpViewModel.loadProducts() }
        }

        is ContentType.Loading -> {
            FullScreenLoading()
        }

        is ContentType.Content -> {
            if (contentType.products.isEmpty()) {
                FullScreenMessage(stringResource(R.string.plp_no_products))
            } else {
                BodyContent(plpState, contentType.products)
            }
        }

        else -> {
            Box(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun BodyContent(
    plpState: PLPState,
    products: List<Product>
) {
}
