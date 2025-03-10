package com.denisbrandi.androidrealca.plp.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.denisbrandi.androidrealca.designsystem.*
import com.denisbrandi.androidrealca.money.presentation.view.PriceText
import com.denisbrandi.androidrealca.plp.presentation.viewmodel.*
import com.denisbrandi.androidrealca.plp.ui.R
import com.denisbrandi.androidrealca.product.domain.model.Product

@Composable
internal fun PLPScreen(
    plpViewModel: PLPViewModel
) {
    val plpState by plpViewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopBar(stringResource(R.string.plp_welcome, plpState.fullName))
        },
        bottomBar = { Box(Modifier.size(0.dp)) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Body(plpViewModel, plpState)
        }
    }
    LaunchedEffect(Unit) {
        plpViewModel.loadProducts()
    }
}

@Composable
private fun Body(plpViewModel: PLPViewModel, plpScreenState: PLPScreenState) {
    when (val contentType = plpScreenState.displayState) {
        is DisplayState.Error -> {
            RetryErrorView { plpViewModel.loadProducts() }
        }

        is DisplayState.Loading -> {
            FullScreenLoading()
        }

        is DisplayState.Content -> {
            if (contentType.products.isEmpty()) {
                FullScreenMessage(stringResource(R.string.plp_no_products))
            } else {
                BodyContent(plpViewModel, contentType.products)
            }
        }

        else -> {
            Box(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun BodyContent(
    plpViewModel: PLPViewModel,
    products: List<Product>
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = halfMargin)
    ) {
        items(products) { product ->
            ProductRow(plpViewModel, product)
        }
    }
}

@Composable
private fun ProductRow(
    plpViewModel: PLPViewModel,
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
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
                PriceText(product.money)
            }

            Row(
                modifier = Modifier
                    .padding(start = halfMargin, top = defaultMargin, bottom = defaultMargin)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
                WishlistButton(plpViewModel, product)
                IconButton(
                    onClick = {
                        plpViewModel.addProductToCart(product)
                    }
                ) {
                    Icon(
                        painterResource(com.denisbrandi.androidrealca.designsystem.R.drawable.baseline_add_shopping_cart_24),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun WishlistButton(
    plpViewModel: PLPViewModel,
    product: Product
) {
    var isFavourite by remember {
        mutableStateOf(plpViewModel.isFavourite(product.id))
    }
    val painter: Painter
    val updateWishlist: () -> Unit
    if (isFavourite) {
        painter =
            painterResource(R.drawable.baseline_favorite_24)
        updateWishlist = {
            plpViewModel.removeProductFromWishlist(product.id)
            isFavourite = false
        }
    } else {
        painter =
            painterResource(R.drawable.baseline_favorite_border_24)
        updateWishlist = {
            plpViewModel.addProductToWishlist(product)
            isFavourite = true
        }
    }

    IconButton(
        onClick = {
            updateWishlist()
        }
    ) {
        Icon(painter, contentDescription = null)
    }
}
