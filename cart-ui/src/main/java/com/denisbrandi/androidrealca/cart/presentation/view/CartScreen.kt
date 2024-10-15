package com.denisbrandi.androidrealca.cart.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.denisbrandi.androidrealca.cart.domain.model.*
import com.denisbrandi.androidrealca.cart.presentation.viewmodel.CartViewModel
import com.denisbrandi.androidrealca.designsystem.*
import com.denisbrandi.androidrealca.plp.ui.R

@Composable
internal fun CartScreen(
    cartViewModel: CartViewModel
) {
    Scaffold(
        topBar = {
            TopBar(stringResource(R.string.cart_title))
        },
        bottomBar = { Box(Modifier.size(0.dp)) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Body(cartViewModel)
        }
    }
}

@Composable
private fun Body(cartViewModel: CartViewModel) {
    val cartState by cartViewModel.state.collectAsState()
    if (cartState.cart.cartItems.isEmpty()) {
        FullScreenMessage(stringResource(R.string.cart_empty_message))
    } else {
        BodyContent(cartViewModel, cartState.cart)
    }
}

@Composable
private fun BodyContent(
    cartViewModel: CartViewModel,
    cart: Cart
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = halfMargin)
    ) {
        items(cart.cartItems) { item ->
            CartItemRow(cartViewModel, item)
        }
    }
}

@Composable
private fun CartItemRow(
    cartViewModel: CartViewModel,
    cartItem: CartItem
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
                model = cartItem.imageUrl,
                contentDescription = null,
                placeholder = painterResource(com.denisbrandi.androidrealca.designsystem.R.drawable.baseline_image_24),
                error = painterResource(com.denisbrandi.androidrealca.designsystem.R.drawable.baseline_image_24)
            )
            Column(
                modifier = Modifier.padding(start = defaultMargin)
            ) {
                Text(
                    text = cartItem.name,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
                Text(
                    text = "${cartItem.money.currencySymbol}${cartItem.money.amount}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2
                )
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = halfMargin, vertical = defaultMargin)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
            }
        }
    }
}
