package com.denisbrandi.androidrealca.cart.presentation.view

import androidx.compose.foundation.background
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
import com.denisbrandi.androidrealca.cart.ui.R
import com.denisbrandi.androidrealca.designsystem.*
import com.denisbrandi.androidrealca.money.domain.model.Money
import com.denisbrandi.androidrealca.money.presentation.presenter.MoneyPresenter
import com.denisbrandi.androidrealca.money.presentation.view.PriceText

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
    Box(Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = halfMargin)
        ) {
            itemsIndexed(cart.cartItems) { index, item ->
                CartItemRow(cartViewModel, item, index == cart.cartItems.size - 1)
            }
        }
        cart.getSubtotal()?.let { subtotal -> CartSubTotal(subtotal) }
    }
}

@Composable
private fun BoxScope.CartSubTotal(
    subtotal: Money
) {
    Box(
        Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .background(Color.White)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(defaultMargin)
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = stringResource(
                    R.string.cart_subtotal,
                    MoneyPresenter.format(subtotal)
                ),
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
private fun CartItemRow(
    cartViewModel: CartViewModel,
    cartItem: CartItem,
    isLastItem: Boolean
) {
    Card(
        modifier = Modifier
            .padding(
                top = halfMargin,
                start = defaultMargin,
                end = defaultMargin,
                bottom = if (isLastItem) 64.dp else halfMargin
            )
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
                PriceText(cartItem.money)
            }

            Row(
                modifier = Modifier
                    .padding(start = halfMargin, top = defaultMargin, bottom = defaultMargin)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val quantity = cartItem.quantity
                IconButton(
                    onClick = {
                        cartViewModel.updateCartItemQuantity(cartItem.copy(quantity = quantity - 1))
                    }
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_remove_24),
                        contentDescription = null
                    )
                }
                MediumLabel(text = quantity.toString())
                IconButton(
                    onClick = {
                        cartViewModel.updateCartItemQuantity(cartItem.copy(quantity = quantity + 1))
                    }
                ) {
                    Icon(
                        painterResource(R.drawable.baseline_add_24),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
