package com.denisbrandi.androidrealca.wishlist.domain.model

import com.denisbrandi.androidrealca.money.domain.model.Money

fun makeWishlistItem(): WishlistItem {
    return WishlistItem(
        id = "1",
        name = "Wireless Headphones",
        money = Money(99.99, "$"),
        imageUrl = "https://example.com/images/wireless-headphones.jpg"
    )
}
