package com.denisbrandi.androidrealca.plp.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import coil3.compose.AsyncImage
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
    LazyColumn(contentPadding = PaddingValues(vertical = halfMargin)) {
        items(products) { product ->
            ProductRow(product)
        }
    }
}

@Composable
private fun ProductRow(
    product: Product
) {
    Card(
        modifier = Modifier
            .padding(vertical = halfMargin, horizontal = defaultMargin)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
        onClick = { }
    ) {
        Row(
            modifier = Modifier
                .height(cardHeight)
                .padding(horizontal = defaultMargin),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(cardImage),
                model = product.imageUrl,
                contentDescription = null,
                placeholder = painterResource(com.denisbrandi.androidrealca.designsystem.R.drawable.baseline_image_24),
                error = painterResource(com.denisbrandi.androidrealca.designsystem.R.drawable.baseline_image_24)
            )
            Column(
                modifier = Modifier.padding(start = defaultMargin)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 1
                )
                Text(
                    text = "${product.money.currencySymbol}${product.money.amount}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )
            }
        }
    }
}
