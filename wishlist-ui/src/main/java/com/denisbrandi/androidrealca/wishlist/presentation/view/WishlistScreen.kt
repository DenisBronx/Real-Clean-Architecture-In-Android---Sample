package com.denisbrandi.androidrealca.wishlist.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.denisbrandi.androidrealca.designsystem.*
import com.denisbrandi.androidrealca.money.presentation.view.PriceText
import com.denisbrandi.androidrealca.wishlist.domain.model.WishlistItem
import com.denisbrandi.androidrealca.wishlist.presentation.viewmodel.WishlistViewModel
import com.denisbrandi.androidrealca.wishlist.ui.R

@Composable
internal fun WishlistScreen(
    wishlistViewModel: WishlistViewModel
) {
    Scaffold(
        topBar = {
            TopBar(stringResource(R.string.wishlist_title))
        },
        bottomBar = { Box(Modifier.size(0.dp)) }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Body(wishlistViewModel)
        }
    }
}

@Composable
private fun Body(wishlistViewModel: WishlistViewModel) {
    val wishlistState by wishlistViewModel.state.collectAsState()
    if (wishlistState.wishlistItems.isEmpty()) {
        FullScreenMessage(stringResource(R.string.wishlist_empty_message))
    } else {
        BodyContent(wishlistViewModel, wishlistState.wishlistItems)
    }
}

@Composable
private fun BodyContent(
    wishlistViewModel: WishlistViewModel,
    wishlistItems: List<WishlistItem>
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = halfMargin)
    ) {
        items(wishlistItems) { item ->
            WishlistItemRow(wishlistViewModel, item)
        }
    }
}

@Composable
private fun WishlistItemRow(
    wishlistViewModel: WishlistViewModel,
    wishlistItem: WishlistItem
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
                model = wishlistItem.imageUrl,
                contentDescription = null,
                placeholder = painterResource(com.denisbrandi.androidrealca.designsystem.R.drawable.baseline_image_24),
                error = painterResource(com.denisbrandi.androidrealca.designsystem.R.drawable.baseline_image_24)
            )
            Column(
                modifier = Modifier.padding(start = defaultMargin)
            ) {
                Text(
                    text = wishlistItem.name,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 1
                )
                PriceText(wishlistItem.money)
            }

            Row(
                modifier = Modifier
                    .padding(start = halfMargin, top = defaultMargin, bottom = defaultMargin)
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        wishlistViewModel.removeItemFromWishlist(wishlistItem.id)
                    }
                ) {
                    Icon(painterResource(R.drawable.baseline_delete_24), contentDescription = null)
                }
                IconButton(
                    onClick = {
                        wishlistViewModel.addProductToCart(wishlistItem)
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
